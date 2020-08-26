package com.xuecheng.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Alexlou on 2020/6/10.
 */
@Configuration
public class RabbitmqConfig {
    //队列名称
    public static final String QUEUE_INROM_EMAIL = "queue_inform_email";
    public static final String QUEUE_INROM_SMS = "queue_inform_sms";
    public static final String EXCHANGE_TOPICS_INFORM="exchange_topics_inform";
    public static final String ROUTINGKTY_EMAIL="inform.#.email.#";
    public static final String ROUTINGKTY_SMS="inform.#.sms.#";
    //声明交换机
    @Bean(EXCHANGE_TOPICS_INFORM)
    public Exchange EXCHANGE_TOPICS_INFORM(){
        //durable(true) 持久化，mq重启后交换机还在
        return ExchangeBuilder.topicExchange(EXCHANGE_TOPICS_INFORM).durable(true).build();

    }
    //声明QUEUE_INROM_EMAIL队列
    @Bean(QUEUE_INROM_EMAIL)
    public Queue QUEUE_INROM_EMAIL(){
        return new Queue(QUEUE_INROM_EMAIL);
    }
    //声明QUEUE_INROM_SMS队列
    @Bean(QUEUE_INROM_SMS)
    public Queue QUEUE_INROM_SMS(){
        return new Queue(QUEUE_INROM_SMS);
    }

    //绑定QUEUE_INROM_EMAIL队列和交换机
    @Bean
    public Binding BINDING_QUEUE_INROM_EMAIL(@Qualifier(QUEUE_INROM_EMAIL) Queue queue,
                                             @Qualifier(EXCHANGE_TOPICS_INFORM) Exchange exchange){
     return BindingBuilder.bind(queue).to(exchange).with(ROUTINGKTY_EMAIL).noargs();
    }
    //绑定QUEUE_INROM_SMS队列和交换机
    @Bean
    public Binding BINDING_QUEUE_INROM_SMS(@Qualifier(QUEUE_INROM_SMS) Queue queue,
                                             @Qualifier(EXCHANGE_TOPICS_INFORM) Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTINGKTY_SMS).noargs();
    }

}
