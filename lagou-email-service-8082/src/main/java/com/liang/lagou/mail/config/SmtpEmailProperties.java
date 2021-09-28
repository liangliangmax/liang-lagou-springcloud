package com.liang.lagou.mail.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * smtp配置类
 *
 * 项目中可能有多邮箱配置，
 * neuabc，dam和neupals可能都有自己的邮箱配置，因为发送人不一样
 */
@Component
@ConfigurationProperties(prefix = "smtp-mail")
public class SmtpEmailProperties {

    private final Lagou lagou = new Lagou();


    public Lagou getLagou() {
        return lagou;
    }

    @Getter
    @Setter
    @ToString
    public static class Lagou{

        private String host;
        private int port;

        private String defaultEncoding;

        private String protocol;

        private String username;
        private String password;

        private String fromAlias;

        private boolean testConnection;

        private Properties properties;

    }



}
