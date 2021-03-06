package com.cutie.activemqdemo.queue;

import javax.jms.*;
import java.util.concurrent.TimeUnit;

public class ActiveMQQueueConsumer {
    public static void main(String[] args) throws JMSException {
        // 创建连接
        Connection connection = ActiveMQQueueProducer.getConnection();

        // 创建会话
        final Session session = ActiveMQQueueProducer.getSession(connection);

        // 创建队列
        Queue queue = ActiveMQQueueProducer.getQueue(session);

        // 创建 Consumer
        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener(new MessageListener() {

            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println(String.format("[线程：%s][消息编号：%s][消息内容：%s]",
                            Thread.currentThread(), textMessage.getJMSMessageID(), textMessage.getText()));
                } catch (JMSException e) {
                    throw new RuntimeException(e);
                }
            }

        });

        // 关闭
        try {
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException ignore) {
        }
        session.close();
        connection.close();
    }
}
