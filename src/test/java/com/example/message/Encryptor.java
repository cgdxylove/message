package com.example.message;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class Encryptor {
    @Autowired
    StringEncryptor encryptor;
 /*   @Value("${db.username}")
    String dbUsername;
    @Value("${db.password}")
    String dbPassword ;*/

    @Test
    public void getPass() {
        String name = encryptor.encrypt("");
        String password = encryptor.encrypt("");
        System.out.println(name+"----------------");
        System.out.println(password+"----------------");
        /*Assert.assertTrue(name.length() > 0);
        Assert.assertTrue(password.length() > 0);*/
    }

}