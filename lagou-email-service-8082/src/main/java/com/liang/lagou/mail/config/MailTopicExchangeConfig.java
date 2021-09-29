package com.liang.lagou.mail.config;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MailTopicExchangeConfig {

    @Bean
    public TopicExchange mailTopicExchange(){
        return new TopicExchange("mailTopicExchange");
    }

}
