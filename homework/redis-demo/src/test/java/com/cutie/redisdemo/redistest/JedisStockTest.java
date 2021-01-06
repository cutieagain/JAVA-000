package com.cutie.redisdemo.redistest;

import com.cutie.redisdemo.redis.JedisUtil;

/**
 * 在 Java 中实现一个分布式计数器，模拟减库存。
 */
public class JedisStockTest {

    public static void main(String[] args) throws InterruptedException {
        new JedisStockTest().decreaseStockTest();
    }

    public void decreaseStockTest() throws InterruptedException {
        JedisUtil jedisUtil = new JedisUtil();
        jedisUtil.initStock("stock", 15);
        new DecreaseStockThread().start();
        new DecreaseStockThread().start();
        new DecreaseStockThread().start();
        new DecreaseStockThread().start();
        new DecreaseStockThread().start();
        new DecreaseStockThread().start();
        new DecreaseStockThread().start();
    }
}

class DecreaseStockThread extends Thread {
    JedisUtil jedisUtil = new JedisUtil();
    @Override
    public void run() {
        while (jedisUtil.setDecr("stock") > 0){//这里的操作是原子性的，下面的库存是不准的
            System.out.println("库存扣减成功，当前库存：" + jedisUtil.getValue("stock"));
        }

    }
}