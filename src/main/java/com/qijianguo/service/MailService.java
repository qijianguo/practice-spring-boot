package com.qijianguo.service;

import javax.mail.MessagingException;

public interface MailService {

    public void sendHtmlMail(String to, String from, String subject, String content) throws MessagingException;

    public void sendAttachmentsMail(String to, String from, String subject, String content, String filePath) throws MessagingException;

    public void sendInlinResourceResource(String to, String from, String subject, String content, String rscPath, String rscId) throws MessagingException;
}
