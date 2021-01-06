package com.cutie.redisdemo.redis;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class JedisUtil {
    /**
     * 客户端jedis
     */
    private Jedis jedis;
    /**
     * 是否占有锁
     */
    private volatile boolean locked = false;
    /**
     * 获取锁的lua脚本
     */
    private static final String LUA_LOCK_SCRIPT = "if redis.call('setnx',KEYS[1],ARGV[1]) == 1 then redis.call('expire',KEYS[1],ARGV[2]) return 1 else return 0 end";
    /**
     * 删除key的lua脚本
     */
    private static final String LUA_DEL_SCRIPT = "if redis.call('GET',KEYS[1]) == ARGV[1] then return redis.call('DEL',KEYS[1]) else return 0 end";
    /**
     * 扣减库存的lua脚本
     */
//    private static final String LUA_DECREASE_SCRIPT = "if (redis.call('exists', KEYS[1]) == 1) then local stock = tonumber(redis.call('get', KEYS[1])) if (stock == -1) then return 1; end; if (stock > 0) thenredis.call('incrby', KEYS[1], -1); return stock; end; return 0; end; return -1";
    private static final String LUA_DECREASE_SCRIPT = "if (redis.call('exists', KEYS[1]) == 1) then " +
            "local stock = tonumber(redis.call('get', KEYS[1])); " +
            "if (stock == -1) then return 1; " +
            "end; " +
            "if (stock > 0) then redis.call('incrby', KEYS[1], -1); return stock; " +
            "end; " +
            "return 0; " +
            "end; " +
            "return -1";

    public JedisUtil(){
        this.jedis = new Jedis("localhost", 6379);
    }

    public Jedis getJedis(){
        return this.jedis;
    }

//    可能会造成死锁的情况
//    setnx和expire是分开的两步操作，不具有原子性，如果执行完第一条指令应用异常或者重启了，锁将无法过期
//    改善方案就是使用Lua脚本来保证原子性（包含setnx和expire两条指令）
//    下面这个是可以的

    /**
     * set值
     * 说明：这个命令仅在不存在key的时候才能被执行成功（NX选项），并且这个key有一个自动失效时间（PX属性）
     *      这个key的值是一个唯一值，这个值在所有的客户端必须是唯一的，所有同一key的获取者（竞争者）这个值都不能一样。
     *
     * @param value
     * @return
     */
    public String setNX(String key, final String value, int expire){
        SetParams setParams = new SetParams();
        setParams.nx();
        setParams.ex(expire);
        return this.jedis.set(key, value, setParams);
    }

    /**
     * 获取锁
     * @return
     */
    public boolean lock(String key, String uniqueId, int expire) throws InterruptedException {
        while (expire>0){
            //获取锁,返回OK则代表获取锁成功
            if("OK".equals(this.setNX(key, uniqueId, expire))){
                System.out.println("lock 获得锁");
                this.locked=true;
                return true;
            }
            //等待过期的时间
            Thread.sleep(expire);
        }
        return false;
    }

    /**
     * 获取锁
     * @param key
     * @param uniqueId
     * @param expire
     * @return
     */
    public boolean tryLockByLua(String key, String uniqueId, int expire) throws InterruptedException {
        List<String> keys = new ArrayList<>();
        List<String> values = new ArrayList<>();
        keys.add(key);
        values.add(uniqueId);
        values.add(String.valueOf(expire));
        long result = (long) jedis.eval(LUA_LOCK_SCRIPT, keys, values);
        //判断是否成功
        if(result>0){
            System.out.println("tryLockByLua 获取锁");
            this.locked=true;
        }
        //等待过期的时间
        Thread.sleep(expire);
        return false;
    }

    /**
     * 释放锁
     * 说明：通过key和唯一value值删除
     * @return
     */
    public void releaseLockByLua(String key, String uniqueId){
        if(this.locked){
            long result= (long) this.jedis.eval(LUA_DEL_SCRIPT,1, key, uniqueId);
            if(result>0){
                System.out.println("releaseLockByLua 正常释放锁");
                this.locked=false;
            }else{
                System.out.println("releaseLockByLua 超时释放锁");
                this.locked=false;
            }
        }
    }

    public long setDecr(String key) {
        // 脚本里的KEYS参数
        List<String> keys = new ArrayList<>();
        keys.add(key);
        // 脚本里的ARGV参数
        List<String> args = new ArrayList<>();
        long result= (long) this.jedis.eval(LUA_DECREASE_SCRIPT, keys, args);
        return result;
    }

    public void initStock(String key, int stock){
        this.jedis.set(key, String.valueOf(stock));
    }

    public String getValue(String key){
        return this.jedis.get(key);
    }

}
