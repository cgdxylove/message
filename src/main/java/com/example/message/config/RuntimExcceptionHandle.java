package com.example.message.config;

import com.example.message.common.beans.UnifiedException;
import com.example.message.common.beans.WebResponse;
import com.google.common.collect.Lists;
import lombok.extern.log4j.Log4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

@Aspect
@Component
@Log4j
public class RuntimExcceptionHandle {
    private final String POINT_CUT = "execution(public * com.example.message.controller..*.*(..))";
    public static Properties props;
    static{
            ClassPathResource resource = new ClassPathResource("/properties/exception.code.properties");
            try {
                props = PropertiesLoaderUtils.loadProperties(new EncodedResource(resource, "UTF-8"));
            } catch (IOException e) {
                log.error("加载异常码配置文件【exception.code.properties】出错：", e);
            }
        }


    @Pointcut(POINT_CUT)
    public void pointCut(){}

    @Around("pointCut()")
         public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable{
         WebResponse apiResponse = null;
            try {
                log.info("执行Controller开始: " + pjp.getSignature() + " 参数：" + Lists.newArrayList(pjp.getArgs()).toString());
                //执行访问接口操作
                apiResponse = (WebResponse) pjp.proceed(pjp.getArgs());
            } catch (Exception throwable) {
                apiResponse = handlerException(pjp, throwable);
            }
            return apiResponse;
        }

    private WebResponse handlerException(ProceedingJoinPoint pjp, Throwable e) {
        WebResponse apiResponse = new WebResponse();
        if(e.getClass().isAssignableFrom(UnifiedException.class) ){
            UnifiedException unifiedException = (UnifiedException)e;
            String code = unifiedException.getCode();
            apiResponse.error(code , props.get(code).toString());
        }else {
            log.error("异常{方法：" + pjp.getSignature() + "， 参数：" + pjp.getArgs() + ",异常：" + e.getMessage() + "}", e);
            apiResponse.error("1111","系统异常");
        }
        return apiResponse;
    }
}
