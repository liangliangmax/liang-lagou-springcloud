package com.liang.lagou.mail.sender;

import com.liang.lagou.mail.config.MailSenderFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 系统启动时候会将JavaMailSender类型的bean添加到mailSenderFactory中
 *
 */
@Slf4j
@Component
public class MailSenderAnnotationProcessor implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private MailSenderFactory mailSenderFactory;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if(event.getApplicationContext().getParent().getParent() == null){

            Map<String, JavaMailSender> beans =  event.getApplicationContext().getBeansOfType(JavaMailSender.class);//获取全部ControllerBean

            Set<Map.Entry<String, JavaMailSender>> entries = beans.entrySet();//遍历Bean
            Iterator<Map.Entry<String, JavaMailSender>> iterator = entries.iterator();

            while(iterator.hasNext()){
                Map.Entry<String, JavaMailSender> map = iterator.next();

                log.info(map.getKey()+"被加入了mailSenderFactory中");

                mailSenderFactory.addSender(map.getKey(),map.getValue());
            }


        }

    }
}
