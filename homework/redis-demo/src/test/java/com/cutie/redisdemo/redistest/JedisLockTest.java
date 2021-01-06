package com.cutie.redisdemo.redistest;

import com.cutie.redisdemo.redis.JedisUtil;

import java.util.UUID;

/**
 * 在 Java 中实现一个简单的分布式锁
 */
public class JedisLockTest {

    public static void main(String[] args) throws InterruptedException {
        new JedisLockTest().jedisLockTest();
    }

    public void jedisLockTest() throws InterruptedException {
        String key = "key";
        String uniqueId = UUID.randomUUID().toString();
        JedisUtil jedisUtil = new JedisUtil();
        try {
            //获得锁不释放3秒后释放
            boolean lockTest = jedisUtil.tryLockByLua(key, uniqueId, 3);
            //直接释放锁
//            jedisLock.releaseLockByLua(key, uniqueId);
            lockTest = jedisUtil.lock(key, uniqueId, 1);
            if (!lockTest) {
                System.out.println("locked error");
                return;
            }
            //do something
        } finally {
            jedisUtil.releaseLockByLua(key, uniqueId);
        }
    }

}