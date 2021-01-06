package com.cutie.redisdemo.redistest;

import com.cutie.redisdemo.pubsub.Publisher;
import com.cutie.redisdemo.pubsub.Subscriber;
import com.cutie.redisdemo.redis.JedisUtil;
import redis.clients.jedis.Jedis;

public class PubSubTest {
    public static final String CHANNEL = "orderChannel";

    public static void main(String[] args) throws InterruptedException {
        new PubSubTest().pubSubTest();
    }

    public void pubSubTest() throws InterruptedException {
        Subscriber subscriber = new Subscriber();
        new Thread(() -> {
            while (true){
                try {
                    Jedis subscriberJedis = new JedisUtil().getJedis();
                    System.out.println("Subscribing to orderChannel, this thread will be block");
                    subscriberJedis.subscribe(subscriber, CHANNEL);
                    System.out.println("subscription ended");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Publisher().publish("orderChannel", "orderNo001");
//        subscriber.unsubscribe();
    }
}
