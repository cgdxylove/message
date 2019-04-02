package com.example.message;

import com.example.message.entitys.mail.Inline;
import com.example.message.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SendTest {
    @Resource
    MailService mailService ;
    @Resource
    TemplateEngine engine ;

    @Test
    public void sendSimpleTest(){
        mailService.sendSimpleMail("458089462@qq.com","邮件测试","邮件内容：这是邮件测试内容");
    }

    @Test
    public void sendHtmlTest() throws MessagingException {
        String content = "<html>" +
                "<body>" +
                "<h2>Html邮件测试2</h2>\n" +
                "<h3>Html邮件测试3</h3>\n" +
                "</body>" +
                "</html>";
        mailService.sendHTMLMail("458089462@qq.com","HTML邮件测试",content);
    }


    @Test
    public void sendAttachmentsTest() throws MessagingException {
        String content = "<html>" +
                "<body>" +
                "<h2>Html邮件测试2</h2>\n" +
                "<h3>Html邮件测试3</h3>\n" +
                "</body>" +
                "</html>";
        mailService.sendAttachmentsMail("458089462@qq.com","附件邮件测试",content,new String[]{"D://Test.java","D://Test.class"});
    }

    @Test
    public void sendInlineTest() throws MessagingException {
        List<Inline> list = new ArrayList<>();
        Inline inline = new Inline();

        inline.setRscPath("C:\\Users\\Lenovo\\Desktop\\20190308.jpg");
        inline.setCid("cg");
        list.add(inline);
        String content = "<html>" +
                "<body>" +
                "<img src=\'cid:"+inline.getCid()+"\'></img>"+
                "</body>" +
                "</html>";
        mailService.sendInlineResourceMail("986464561@qq.com","图片邮件测试",content,list);
    }

    @Test
    public void sendThymeleafTest() throws MessagingException {
        List<Inline> list = new ArrayList<>();
        Inline inline = new Inline();
        inline.setRscPath("C:\\Users\\Lenovo\\Desktop\\20190308.jpg");
        inline.setCid("cg");
        list.add(inline);
        Context context = new Context();
        context.setVariable("cid","cg");
        String emailContent = engine.process("emailThymeleafExample",context);
        mailService.sendInlineResourceMail("458089462@qq.com","模板邮件测试",emailContent,list);
    }



}