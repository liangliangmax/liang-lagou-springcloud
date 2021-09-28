package com.liang.lagou.mail.util;

import com.liang.lagou.mail.config.MailSenderFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.mail.util.ByteArrayDataSource;
import java.io.File;
import java.util.Base64;
import java.util.Map;

@Slf4j
@Component
public class SmtpMailUtil implements IMailUtil {

    @Autowired
    private MailSenderFactory mailSenderFactory;

    static {
        System.getProperties().setProperty("mail.mime.splitlongparameters", "false");
    }

    /**
     * 发送文本邮件
     * @param moduleName 模块名称，通过模块名称来判断调用哪个邮箱发送邮件
     * @param to         收件人邮箱
     * @param subject    邮件主题
     * @param content    邮件正文
     * @param fromAlias  收件人邮箱中显示的发件人邮箱的昵称
     * @param cc         抄送
     * @throws Exception
     */
    @Override
    public void sendSimpleMail(String moduleName,String to, String subject, String content,String fromAlias, String[] cc, String[] bcc) throws Exception{

        JavaMailSenderImpl mailSender = (JavaMailSenderImpl)mailSenderFactory.getMailSender(moduleName+"-mailSender");

        if(mailSender == null){
            mailSender = (JavaMailSenderImpl)getDefault();
        }

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        if(StringUtils.isBlank(fromAlias)){
            helper.setFrom(mailSender.getUsername());
        }else {
            helper.setFrom(new InternetAddress(fromAlias+" <"+mailSender.getUsername()+">"));
        }


        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, false);

        //抄送
        if(cc!=null && cc.length > 0){
            helper.setCc(cc);
        }

        /** 密送 */
        if (bcc != null && bcc.length > 0) {
            helper.setBcc(bcc);
        }

        mailSender.send(message);

    }


    /**
     * 发送HTML邮件
     * @param moduleName 模块名称，通过模块名称来判断调用哪个邮箱发送邮件
     * @param to         收件人邮箱
     * @param subject    邮件主题
     * @param content    邮件正文
     * @param fromAlias  收件人邮箱中显示的发件人邮箱的昵称
     * @param cc         抄送
     */
    @Override
    public void sendHtmlMail(String moduleName, String to, String subject, String content,String fromAlias, String[] cc, String[] bcc) throws Exception {

        JavaMailSenderImpl mailSender = (JavaMailSenderImpl)mailSenderFactory.getMailSender(moduleName+"-mailSender");

        if(mailSender == null){
            mailSender = (JavaMailSenderImpl)getDefault();
        }

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        if(StringUtils.isBlank(fromAlias)){
            helper.setFrom(mailSender.getUsername());
        }else {
            helper.setFrom(new InternetAddress(fromAlias+" <"+mailSender.getUsername()+">"));
        }


        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);

        //抄送
        if(cc!=null && cc.length > 0){
            helper.setCc(cc);
        }

        /** 密送 */
        if (bcc != null && bcc.length > 0) {
            helper.setBcc(bcc);
        }
        mailSender.send(message);

    }

    /**
     * 发送带附件的邮件
     * @param moduleName    模块名称，通过模块名称来判断调用哪个邮箱发送邮件
     * @param to            收件人邮箱
     * @param subject       邮件主题
     * @param content       邮件正文
     * @param fromAlias     收件人邮箱中显示的发件人邮箱的昵称
     * @param fileName      附件名称
     * @param fileType      附件类型
     * @param fileBase64Str 附件Base64字符串
     * @param cc            抄送
     * @throws Exception
     */
    @Override
    public void sendAttachmentsMail(String moduleName, String to, String subject, String content,String fromAlias, String fileName, String fileType, String fileBase64Str, String[] cc, String[] bcc) throws Exception {
        JavaMailSenderImpl mailSender = (JavaMailSenderImpl)mailSenderFactory.getMailSender(moduleName+"-mailSender");

        if(mailSender == null){
            mailSender = (JavaMailSenderImpl)getDefault();
        }

        //支持附件名字过长
        System.getProperties().setProperty("mail.mime.splitlongparameters", "false");

        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");

        if(StringUtils.isBlank(fromAlias)){
            helper.setFrom(mailSender.getUsername());
        }else {
            helper.setFrom(new InternetAddress(fromAlias+" <"+mailSender.getUsername()+">"));
        }

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);

        //抄送
        if(cc!=null && cc.length > 0){
            helper.setCc(cc);
        }

        /** 密送 */
        if (bcc != null && bcc.length > 0) {
            helper.setBcc(bcc);
        }

        if(StringUtils.isNotBlank(fileBase64Str)){
            helper.addAttachment(MimeUtility.encodeWord(fileName,"utf-8","B"), new ByteArrayDataSource(Base64.getDecoder().decode(fileBase64Str),fileType));
        }

        mailSender.send(message);

    }

    @Override
    public void sendAttachmentsMail(String moduleName, String to, String subject, String content, String fromAlias, String fileName,File file, String[] cc, String[] bcc) throws Exception {

        JavaMailSenderImpl mailSender = (JavaMailSenderImpl)mailSenderFactory.getMailSender(moduleName+"-mailSender");

        if(mailSender == null){
            mailSender = (JavaMailSenderImpl)getDefault();
        }

        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        if(StringUtils.isBlank(fromAlias)){
            helper.setFrom(mailSender.getUsername());
        }else {
            helper.setFrom(new InternetAddress(fromAlias+" <"+mailSender.getUsername()+">"));
        }

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);

        //抄送
        if(cc!=null && cc.length > 0){
            helper.setCc(cc);
        }

        /** 密送 */
        if (bcc != null && bcc.length > 0) {
            helper.setBcc(bcc);
        }

        helper.addAttachment(fileName, file);

        mailSender.send(message);

    }


    /**
     * 发送正文中有静态资源的邮件
     * 具体用法可以参考test类
     * @param moduleName 模块名称，通过模块名称来判断调用哪个邮箱发送邮件
     * @param to         收件人邮箱
     * @param subject    邮件主题
     * @param content    邮件正文
     * @param fromAlias  收件人邮箱中显示的发件人邮箱的昵称
     * @param rscPath
     * @param rscId
     * @param cc         抄送
     */
    @Override
    public void sendResourceMail(String moduleName, String to, String subject, String content,String fromAlias, Map<String, String> rscIdMap, String[] cc, String[] bcc) throws Exception {

        JavaMailSenderImpl mailSender = (JavaMailSenderImpl)mailSenderFactory.getMailSender(moduleName+"-mailSender");

        if(mailSender == null){
            mailSender = (JavaMailSenderImpl)getDefault();
        }

        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        if(StringUtils.isBlank(fromAlias)){
            helper.setFrom(mailSender.getUsername());
        }else {
            helper.setFrom(new InternetAddress(fromAlias+" <"+mailSender.getUsername()+">"));
        }

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);

        //抄送
        if(cc!=null && cc.length > 0){
            helper.setCc(cc);
        }

        /** 密送 */
        if (bcc != null && bcc.length > 0) {
            helper.setBcc(bcc);
        }

        for (Map.Entry<String, String> entry : rscIdMap.entrySet()) {
            FileSystemResource file = new FileSystemResource(new File(entry.getValue()));
            helper.addInline(entry.getKey(), file);
        }

        mailSender.send(message);

    }


    //如果没有指定模块名称，则调用默认的发送邮箱
    private JavaMailSender getDefault(){

        return mailSenderFactory.getMailSender("lagou-mailSender");
    }

}
