package com.springboot09redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class test {

    @Autowired
    StringRedisTemplate redisTemplate;

    @GetMapping("/sk")
    public String sk(Model model){
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        String s = operations.get("/");
        System.out.println(s);
        return "2222";
    }
}
