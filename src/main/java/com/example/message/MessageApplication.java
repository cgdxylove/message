package com.example.message;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableAutoConfiguration
@EnableEurekaClient
@MapperScan("com.example.message.dao")//将项目中对应的mapper类的路径加进来就可以了
public class MessageApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageApplication.class, args);
	}

	/*@Bean //定义REST客户端，RestTemplate实例
	@LoadBalanced//开启负债均衡的能力
	RestTemplate restTemplate() {
		return new RestTemplate();
	}*/

}
