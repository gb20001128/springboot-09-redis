package com.springboot09redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@Controller
public class ListController {

    @Autowired
    StringRedisTemplate redisTemplate;

    @GetMapping("/list")
    public String list(){
        return "addlist";
    }

    @GetMapping("/listAll")
    public String listAll(Model model){
        ListOperations<String, String> operations  = redisTemplate.opsForList();
        List<String> names = operations.range("name", 0, -1);
        model.addAttribute("listAll",names);
        return "listAll";
    }

    @PostMapping("/add")
    public String add(){
        return "addlist";
    }

    @PostMapping("/addList")
    public String addList(String name , Model model){
        if(name==""){ model.addAttribute("error","不能提交空名字！"); return "addlist";}
        ListOperations<String, String> operations  = redisTemplate.opsForList();
        operations.rightPush("name", name);
        List<String> names = operations.range("name", 0, -1);
        //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        model.addAttribute("listAll",names);
        //model.addAttribute("time",df.format(new Date()));
        return "redirect:/listAll";
    }

    @GetMapping("/del/{name}")
    public String delete(@PathVariable("name")String name, Model model){
        System.out.println(name);
        ListOperations<String, String> operations  = redisTemplate.opsForList();
        operations.remove("name",1,name);
        return "redirect:/listAll";
    }

}
