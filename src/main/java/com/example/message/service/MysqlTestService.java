package com.example.message.service;

import com.example.message.common.beans.WebResponse;
import com.example.message.dao.TestDao;
import com.example.message.dao.TestMapper;
import com.example.message.entitys.bean.Test;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j
public class MysqlTestService {
    @Autowired
    private TestDao testDao;
    @Autowired
    private TestMapper testMapper;

    public WebResponse testMysqlService(){
        WebResponse response = new WebResponse();
        /*log.info("================= 测试mysql数据库有没有调通");
        Map map = new HashMap<>();
        map.put("lx","ybqy_fzb");
        List<Test> list =  testMapper.testSelect(map);
        for(int i=0;i<list.size();i++){
            System.out.println("//"+list.get(i).getName());
            System.out.println("private String "+list.get(i).getValue() +" ;");
        }*/
        List<String> list =new ArrayList<>();
        list.add("test1");
        list.add("test2");
        response.success("0000","ceshi",list);
        return response;
    }

    @Transactional
    public void testInsertService(){
        Test test = new Test();
        test.setId(2);
        test.setName("666");
        testMapper.insert(test);
        //int i = 1/0;  // 抛出异常
    }
}