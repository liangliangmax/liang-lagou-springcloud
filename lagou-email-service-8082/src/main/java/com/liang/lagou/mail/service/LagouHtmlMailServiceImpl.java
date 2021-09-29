package com.liang.lagou.mail.service;


import com.liang.lagou.mail.config.SmtpEmailProperties;
import com.liang.lagou.mail.dto.MailInfoDto;
import com.liang.lagou.mail.util.IMailUtil;
import com.sun.tracing.dtrace.ModuleName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * html邮件的发送工具类，如果是别的发送类型，可以再写一个service
 */
@Slf4j
@Service("damHtmlMailService")
public class LagouHtmlMailServiceImpl implements IMailService {

    @Autowired
    private IMailUtil mailUtil;

    @Autowired
    private SmtpEmailProperties smtpEmailProperties;

    @Override
    public void sendMail(MailInfoDto mailInfoDto) throws Exception {
        try {

            String moduleName = "lagou";

            String fromAlias = "max";

            String to = mailInfoDto.getTo();

            String subject = mailInfoDto.getSubject();

            String content = mailInfoDto.getContent();

            String cc = mailInfoDto.getCc();

            String bcc = mailInfoDto.getBcc();

            mailUtil.sendHtmlMail(moduleName,to,subject,content,fromAlias,parseArray(cc),parseArray(bcc));


            log.info("邮件发送成功，内容为："+mailInfoDto.toString());
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            log.error("错误邮件内容为："+mailInfoDto.toString());

            throw e;
        }
    }
}
