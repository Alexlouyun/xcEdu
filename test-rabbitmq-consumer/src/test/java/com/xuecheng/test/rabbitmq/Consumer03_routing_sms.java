package com.xuecheng.test.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 入门程序消费方
 * Created by Alexlou on 2020/6/9.
 */
public class Consumer03_routing_sms {
    //队列名称
    private static final String QUEUE_INROM_SMS = "queue_inform_sms";
    private static final String EXCHANGE_ROUTING_INFORM="exchange_routing_inform";
    private static final String ROUTINGKTY_SMS="inform.#.sms.#";
    public static void main(String[] args) throws IOException, TimeoutException {
        //通过连接工厂创建新的连接和MQ连接
        ConnectionFactory connectionFactory =new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        //设置虚拟机，一个mq服务可以设置多个虚拟机，每个虚拟机都是独立的mq
        connectionFactory.setVirtualHost("/");



            //建立新连接
            Connection connection = connectionFactory.newConnection();
            //创建通道，生产者和mq服务所有的通信都是在通道中完成
            Channel channel = connection.createChannel();
            //声明队列
            //参数：String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments
            /*
             * 参数明细
             * 1、queue 队列名称
             * 2、durable 是否持久化，如果持久化，则mq重启后队列还在。
             * 3、exclusive 是否独占连接，队列只允许在该链接中访问，如果Connection连接关闭则自动删除，
             * 4、autoDelete 是否自动删除，
             * 5、 arguments 可扩展参数，比如存活时间
             *
             * */
            channel.queueDeclare(QUEUE_INROM_SMS,true,false,false,null);
            channel.exchangeDeclare(EXCHANGE_ROUTING_INFORM,BuiltinExchangeType.DIRECT);
            channel.queueBind(QUEUE_INROM_SMS,EXCHANGE_ROUTING_INFORM,ROUTINGKTY_SMS);
            //定义消费方法
            DefaultConsumer defaultConsumer = new DefaultConsumer(channel){
                /**
                 * 消费者接收消息调用此方法
                 * @param consumerTag 消费者的标签，在channel.basicConsume()去指定
                 * @param envelope
                 * @param properties
                 * @param body
                 * @throws IOException
                 */
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    //交换机
                    String exchange = envelope.getExchange();
//路由key
                    String routingKey = envelope.getRoutingKey();
//消息id
                    long deliveryTag = envelope.getDeliveryTag();
//消息内容
                    String msg = new String(body);
                    System.out.println("receive message.." + msg);
                }
            };

            /*
            * 参数：String queue, boolean autoAck, Consumer callback
            * 参数明细：
            * 1、queue 队列名称
            * 2、autoAck 是否自动回复，当消费方接收到消息后要告诉mq已接收消息，如果设置成true，表示自动回复，如果设置成false则编程回复
            * 3、callback 消息消费的方法，消费者接收到消息后调用的方法。
            *
            * */
            channel.basicConsume(QUEUE_INROM_SMS,true,defaultConsumer);
    }
}
