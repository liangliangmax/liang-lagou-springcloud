package com.liang.lagou.mail.config;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 因为系统中可能会出现多个发送邮箱，所以需要将所有的邮箱都扫描到
 *
 * 通过传递过来的参数动态的调用某个邮箱的sender
 */
@Component
public class MailSenderFactory {

    private Map<String, JavaMailSender> senders = new HashMap<>();

    public void addSender(String senderName, JavaMailSender javaMailSender){
        this.senders.put(senderName,javaMailSender);
    }

    public JavaMailSender getMailSender(String senderName){

        return this.senders.get(senderName);
    }

    public Map<String, JavaMailSender> getSenders() {
        return senders;
    }


}
