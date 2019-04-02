package com.example.message.controller;

import com.example.message.common.beans.WebResponse;
import com.example.message.service.MysqlTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MysqlTestController {

    @Autowired
    private MysqlTestService testService ;

    @GetMapping(value="/testMysql")
    @ResponseBody
    public WebResponse test() {
       return testService.testMysqlService();
    }

    @GetMapping(value="/testInsert")
    @ResponseBody
    public  void testInsert(){
        testService.testInsertService();
    }
}