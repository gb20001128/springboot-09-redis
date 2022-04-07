package com.springboot09redis;

import com.springboot09redis.config.RedisConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.ui.Model;

@SpringBootTest
class Springboot09RedisApplicationTests {

    @Autowired
    StringRedisTemplate redisTemplate;



    @Test
    void testRedis(){
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set("hello","world");
        String hello = operations.get("hello");
        System.out.println(hello);
        HashOperations<String, Object, Object> operation = redisTemplate.opsForHash();
        operation.put("user","name","zhangsan");
        System.out.println(operation.get("user","name"));

    }


}
