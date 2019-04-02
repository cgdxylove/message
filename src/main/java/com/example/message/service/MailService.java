package com.example.message.service;

import com.example.message.entitys.mail.Inline;
import com.example.message.utils.EmptyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;

@Service
public class MailService {
    @Value("${spring.mail.username}")
    private String from ;

    @Autowired
    private JavaMailSender javaMailSender ;

    public void sendSimpleMail(String to,String subject,String content){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(content);
        mailMessage.setFrom(from);
        javaMailSender.send(mailMessage);
    }

    public void sendHTMLMail(String to,String subject,String content) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true);
        messageHelper.setFrom(from);
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        messageHelper.setText(content,true);
        javaMailSender.send(mimeMessage);
    }

    public void sendAttachmentsMail(String to,String subject,String content,String[] filePaths) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true);
        messageHelper.setFrom(from);
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        messageHelper.setText(content,true);
        if(filePaths.length>0){
            for(int i=0;i<filePaths.length;i++){
                String  filePath  = filePaths[i] ;
                FileSystemResource file = new FileSystemResource(new File(filePath));
                String fileName = file.getFilename();//名称可以随意取，并非一定要原始文件名称
                messageHelper.addAttachment(fileName,file);
            }
        }
        javaMailSender.send(mimeMessage);
    }

    public void sendInlineResourceMail(String to,String subject,String content,
                                      List<Inline> inlines) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true);
        messageHelper.setFrom(from);
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        messageHelper.setText(content,true);
        if(!EmptyUtils.isEmpty(inlines)){
            for(int i=0;i<inlines.size();i++){
                Inline inline = inlines.get(i);
                FileSystemResource file = new FileSystemResource(new File(inline.getRscPath()));
                messageHelper.addInline(inline.getCid(),file);
            }
        }
        javaMailSender.send(mimeMessage);
    }



}