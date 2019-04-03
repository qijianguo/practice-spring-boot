package com.qijianguo.service.impl;

import com.qijianguo.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class MailServiceImpl implements MailService {

    @Autowired(required = true)
    private JavaMailSender mailSender;

    @Override
    public void sendHtmlMail(String to, String from, String subject, String content) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);
        mailSender.send(mimeMessage);
    }

    @Override
    public void sendAttachmentsMail(String to, String from, String subject, String content, String filePath) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);

        FileSystemResource resource = new FileSystemResource(new File(filePath));
        String fileName = resource.getFilename();
        helper.addAttachment(fileName, resource);
        mailSender.send(mimeMessage);
    }

    @Override
    public void sendInlinResourceResource(String to, String from, String subject, String content, String rscPath, String rscId) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);

        FileSystemResource resource = new FileSystemResource(new File(rscPath));
        String fileName = resource.getFilename();
        helper.addInline(rscId, resource);
        mailSender.send(mimeMessage);
    }
}
