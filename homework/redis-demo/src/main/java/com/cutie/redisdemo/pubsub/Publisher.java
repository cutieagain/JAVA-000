package com.cutie.redisdemo.pubsub;

import com.cutie.redisdemo.redis.JedisUtil;
import redis.clients.jedis.Jedis;

public class Publisher {
    public void publish(String channel, String orderNo){
        Jedis publisherJedis = new JedisUtil().getJedis();
        publisherJedis.publish(channel, orderNo);
    }
}
