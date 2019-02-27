package com.qijianguo.service;

public interface RedisEntity {
    String generateRedisKey();
    String generateRedisKey(Object key);
}