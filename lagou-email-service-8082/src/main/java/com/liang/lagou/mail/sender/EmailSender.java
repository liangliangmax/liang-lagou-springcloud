package com.liang.lagou.mail.sender;

import com.liang.lagou.mail.config.SmtpEmailProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {

    /**
     * lagou邮箱的发送配置类
     * @param emailProperties
     * @return
     */
    @Bean(name = "lagou-mailSender")
    public JavaMailSender neuabcMailSender(SmtpEmailProperties emailProperties) {

        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        SmtpEmailProperties.Lagou lagou = emailProperties.getLagou();

        javaMailSender.setUsername(lagou.getUsername());
        javaMailSender.setPassword(lagou.getPassword());

        javaMailSender.setHost(lagou.getHost());
        javaMailSender.setPort(lagou.getPort());
        javaMailSender.setProtocol(lagou.getProtocol());

        javaMailSender.setJavaMailProperties(lagou.getProperties());
        javaMailSender.setDefaultEncoding(lagou.getDefaultEncoding());

        return javaMailSender;
    }




}
