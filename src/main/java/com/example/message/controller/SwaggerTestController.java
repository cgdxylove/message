package com.example.message.controller;

import com.alibaba.fastjson.JSONArray;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author cg
 * @create 2019-04-29 17:47
 */
@RestController
@RequestMapping("/swagger")
@Api(tags = "swagger测试")
public class SwaggerTestController {

    @PostMapping("/test")
    @ApiOperation(value = "swagger测试接口test", notes = "测试，直接返回参数")
    public String test(@RequestBody Map map) throws Exception {
        return JSONArray.toJSONString(map);
    }

}