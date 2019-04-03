package com.qijianguo.service.impl;

import com.qijianguo.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceImplTest {

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private MailService mailService;

    @Test
    public void sendHtmlMail() {
        try {
            mailService.sendHtmlMail(
                    "1625035872@qq.com",
                    from,
                    "想你了小宝贝",
                    "");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sendAttachmentsMail() {
        try {
            mailService.sendAttachmentsMail(
                    "angustudy@163.com",
                    from,
                    "标题",
                    "<html><h1>一级标题</h1><h2>二级标题</h2></html>",
                    "D:\\workspace\\web\\practice\\spring-boot\\src\\main\\resources\\static\\1537358521640.jpg");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sendInlinResourceResource() {
        try {
            mailService.sendInlinResourceResource(
                    "1625035872@qq.com",
                    from,
                    "想你了小宝贝",
                    "<html><body><img src='cid:love'/></body></html>",
                    "D:\\workspace\\web\\practice\\spring-boot\\src\\main\\resources\\static\\love.png",
                    "love");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void sendHtmlTemplate() throws MessagingException {
        Context context = new Context();
        context.setVariable("id", 1);
        String template = templateEngine.process("mail_template", context);
        mailService.sendHtmlMail(
                "angustudy@163.com",
                from,
                "标题", template);
    }
}