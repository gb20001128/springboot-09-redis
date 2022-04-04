package com.springboot09redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.concurrent.TimeUnit;

@Controller
public class RedisController {


    @Autowired
    StringRedisTemplate redisTemplate;


    @GetMapping("/")
    public String test1(Model model){

        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        String s = operations.get("/");
        model.addAttribute("count",s);
        return "index";
    }

    @ResponseBody
    @GetMapping("/he")
    public String test(){
        ValueOperations<String, String> operations = redisTemplate.opsForValue();

        String s = operations.get("/he");

        return "首页访问量: "+s;
    }

    @GetMapping("/skill")
    public String skill(Model model){
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set("skill","20",5,TimeUnit.SECONDS);
        return "2";
    }

    @PostMapping("/uii")
    public String skill2(Model model, HttpServletRequest request){
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        HttpSession session = request.getSession();

        if(operations.get("skill")==null)
            model.addAttribute("cishu","秒杀时间已到 !您已抢到"+ session.getAttribute("final")+"件商品");
        else {
            operations.decrement("skill");
            int result =20-Integer.parseInt(operations.get("skill"));
            session.setAttribute("final",result);
            model.addAttribute("cishu", "您已秒杀"+result+"件");
        }
        return "2";
    }


}
