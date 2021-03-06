package com.sjs.redis.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sjs.redis.RedisDataSource;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

@Repository("redisDataSource")
public class RedisDataSourceImpl implements RedisDataSource {

	private static final Logger log = Logger.getLogger(RedisDataSourceImpl.class);

	@Autowired
    private ShardedJedisPool shardedJedisPool;

	public ShardedJedis getRedisClient() {
        try {
            ShardedJedis shardJedis = shardedJedisPool.getResource();
            return shardJedis;
        } catch (Exception e) {
            log.error("getRedisClent error", e);
        }
        return null;
    }

    public void returnResource(ShardedJedis shardedJedis) {
//        shardedJedisPool.close();
        shardedJedisPool.returnResource(shardedJedis);
    }

    public void returnResource(ShardedJedis shardedJedis, boolean broken) {
        if (broken) {
//            shardedJedisPool.close();
            shardedJedisPool.returnBrokenResource(shardedJedis);
        } else {
//            shardedJedisPool.close();
            shardedJedisPool.returnResource(shardedJedis);
        }
    }

}
