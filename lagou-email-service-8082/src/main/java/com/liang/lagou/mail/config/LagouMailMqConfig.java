package com.liang.lagou.mail.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * dam 系统
 * mq中队列的绑定关系
 */
@Configuration
public class LagouMailMqConfig {

    @Autowired
    private TopicExchange mailTopicExchange;



    public static final String LAGOU_MAIL_QUEUE = "lagou.mail.queue";

    //暂时未用
    //////////dam邮件队列/////////////////////
    @Bean
    public Queue lagouMailQueue() {
        return new Queue(LAGOU_MAIL_QUEUE,true);
    }


    @Bean
    public Binding bindingMailTopicExchangeForDamCustomer() {
        // 绑定routing队列routingQueueMail到topicExchange交换机,并指定routing路由规则;
        return BindingBuilder.bind(lagouMailQueue()).to(mailTopicExchange)
                .with("lagou.mail");
    }


}
