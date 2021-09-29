package com.liang.lagou.mail.handler;

import com.alibaba.fastjson.JSON;
import com.liang.lagou.mail.dto.MailInfoDto;
import com.liang.lagou.mail.service.IMailService;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class MailHandler {

    @Autowired
    private IMailService mailService;

    @RabbitListener(queues = {
            "lagou.mail.queue"
    })
    @RabbitHandler
    public void sendLagouMail(String message){

        try {
            Map<String,String> sendMailInfo = JSON.parseObject(message,Map.class);

            mailService.sendMail(convert2InfoDto(sendMailInfo));

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(),e);
        }
    }

    private MailInfoDto convert2InfoDto(Map<String,String> sendMailInfo){

        MailInfoDto mailInfoDto = new MailInfoDto();
        mailInfoDto.setTo(sendMailInfo.get("to"));
        mailInfoDto.setCc(sendMailInfo.get("cc"));
        mailInfoDto.setBcc(sendMailInfo.get("bcc"));
        mailInfoDto.setSubject(sendMailInfo.get("subject"));
        mailInfoDto.setContent(sendMailInfo.get("content"));
        mailInfoDto.setFileName(sendMailInfo.get("fileName"));
        mailInfoDto.setFileType(sendMailInfo.get("fileType"));
        mailInfoDto.setFileBase64Str(sendMailInfo.get("fileBase64Str"));
        return mailInfoDto;
    }
}
