package com.xuecheng.test.rabbitmq;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * rabbitmq快速入门程序
 * Created by Alexlou on 2020/6/9.
 */
public class Producer04_topic {
    //队列名称
    private static final String QUEUE_INROM_EMAIL = "queue_inform_email";
    private static final String QUEUE_INROM_SMS = "queue_inform_sms";
    private static final String EXCHANGE_TOPICS_INFORM="exchange_topics_inform";
    private static final String ROUTINGKTY_EMAIL="inform.#.email.#";
    private static final String ROUTINGKTY_SMS="inform.#.sms.#";
    public static void main(String[] args) {
        //通过连接工厂创建新的连接和MQ连接
        ConnectionFactory connectionFactory =new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        //设置虚拟机，一个mq服务可以设置多个虚拟机，每个虚拟机都是独立的mq
        connectionFactory.setVirtualHost("/");

        Connection connection =null;
        Channel channel = null;
        try {
            //建立新连接
            connection = connectionFactory.newConnection();
            //创建通道，生产者和mq服务所有的通信都是在通道中完成
            channel = connection.createChannel();
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
            channel.queueDeclare(QUEUE_INROM_EMAIL,true,false,false,null);
            channel.queueDeclare(QUEUE_INROM_SMS,true,false,false,null);
            //声明交换机
            channel.exchangeDeclare(EXCHANGE_TOPICS_INFORM, BuiltinExchangeType.TOPIC);

            //绑定交换机
            channel.queueBind(QUEUE_INROM_EMAIL,EXCHANGE_TOPICS_INFORM,ROUTINGKTY_EMAIL);
            channel.queueBind(QUEUE_INROM_SMS,EXCHANGE_TOPICS_INFORM,ROUTINGKTY_SMS);
            /*
            * 参数：String exchange, String routingKey, BasicProperties props, byte[] body
            * 参数明细：
            * 1、exchange 交换机，如果不指定将使用默认提供的
            * 2、routingKey，路由key，交换机根据路由key将消息转发到指定队列，如果使用默认交换机，routingKey就是队列名称
            * 3、props 消息的属性
            * 4、body 消息的内容
            * */

      /*      for (int i = 0; i <10 ; i++) {

                String  message = "email inform to user+" + i;
                channel.basicPublish(EXCHANGE_TOPICS_INFORM,"inform.email",null,message.getBytes());
                System.out.println("发送消息到mq：" + message);
            }*/
 /*           for (int i = 0; i <10 ; i++) {

                String  message = "sms inform to user +" + i;
                channel.basicPublish(EXCHANGE_TOPICS_INFORM,"inform.sms",null,message.getBytes());
                System.out.println("发送消息到mq：" + message);
            }*/
            for (int i = 0; i <10 ; i++) {

                String  message = "send inform to user +" + i;
                channel.basicPublish(EXCHANGE_TOPICS_INFORM,"inform.email.sms",null,message.getBytes());
                System.out.println("发送消息到mq：" + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }finally {
            //关闭连接
            //先关闭通道
            try {
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
