package com.qijianguo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MailController {

    @Autowired(required = true)
	private JavaMailSender mailSender;
	
	@GetMapping("/send")
	public String send(){
		//建立邮件消息
		SimpleMailMessage mainMessage = new SimpleMailMessage();
		//发送者
		mainMessage.setFrom("1633972602@qq.com");
		//接收者
		mainMessage.setTo("angustydy@163.com");
		//发送的标题
		mainMessage.setSubject("嗨喽");
		//发送的内容
		mainMessage.setText("hello world");
		mailSender.send(mainMessage);
		return "1";
	}

	public void sendTemplateMail() throws Exception {

		MimeMessage mimeMessage = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom("dyc87112@qq.com");
		helper.setTo("dyc87112@qq.com");
		helper.setSubject("主题：模板邮件");

		Map<String, Object> model = new HashMap<>();
		model.put("username", "didi");
		helper.setText("hello", true);

		mailSender.send(mimeMessage);
	}
}