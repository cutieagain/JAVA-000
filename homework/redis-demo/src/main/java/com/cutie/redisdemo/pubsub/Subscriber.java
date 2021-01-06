package com.cutie.redisdemo.pubsub;

import redis.clients.jedis.JedisPubSub;

public class Subscriber extends JedisPubSub {
    @Override
    public void onMessage(String channel, String message) {
        System.out.println("onMessage == Channel:" + channel + ",Message:" + message);
    }
    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        System.out.println("onSubscribe == channel:" + channel + ",subscribedChannels:" + subscribedChannels);
    }
}
