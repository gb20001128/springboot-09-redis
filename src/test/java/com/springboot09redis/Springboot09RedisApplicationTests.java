package com.springboot09redis;

import com.springboot09redis.config.RedisConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.ui.Model;

import java.util.Set;

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
        HyperLogLogOperations<String, String> logOperations = redisTemplate.opsForHyperLogLog();
        logOperations.add("h9","v1");
        System.out.println(logOperations.size("h3"));

    }

    @Test
    void test1(){
        ZSetOperations<String, String> operations = redisTemplate.opsForZSet();
        Set<ZSetOperations.TypedTuple<String>> idol = operations.reverseRangeByScoreWithScores("idol", 0, 9999999 * 999999);
        for (ZSetOperations.TypedTuple<String> i:idol)
        System.out.println(i.getScore());
    }


}
