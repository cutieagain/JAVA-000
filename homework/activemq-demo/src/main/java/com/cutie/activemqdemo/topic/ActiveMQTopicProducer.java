package com.cutie.activemqdemo.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class ActiveMQTopicProducer {
    private static final String BROKER_URL = "tcp://127.0.0.1:61616";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";

    private static final String TOPIC_NAME = "topic_demo"; // 只有 QUEUE_NAME 需要共享给 RabbitMQConsumer

    public static void main(String[] args) throws JMSException {
        // 创建连接
        Connection connection = getConnection();

        // 创建会话
        Session session = getSession(connection);

        Topic topic = getTopic(session);
        // 创建 Producer
        MessageProducer producerTopic = session.createProducer(topic);

        // 发送 3 条消息
        for (int i = 0; i < 3; i++) {
            Message message = session.createTextMessage("Hello World" + i);
            producerTopic.send(message);
        }

        // 关闭
        session.close();
        connection.close();
    }

    public static Connection getConnection() throws JMSException {
        // 创建连接
        ConnectionFactory factory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKER_URL);
        Connection connection = factory.createConnection();
        // 启动连接
        connection.start();
        return connection;
    }

    public static Session getSession(Connection connection) throws JMSException {
        // 第一个方法参数 transacted ，是否开启事务。这里设置为 false ，无需开启
        // 第二个方法参数 acknowledgeMode ，确认模式。这里设置为 AUTO_ACKNOWLEDGE ，自动确认。推荐阅读 https://my.oschina.net/thinwonton/blog/995291
        return connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    }

    public static Topic getTopic(Session session) throws JMSException {
        return session.createTopic(TOPIC_NAME);
    }
}
