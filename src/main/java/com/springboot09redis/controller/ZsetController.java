package com.springboot09redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Set;

@Controller
public class ZsetController {

    @Autowired
    StringRedisTemplate redisTemplate;

   /* @GetMapping("/list")
    public String list(){
        return "addlist";
    }*/

    @GetMapping("/zsetAll")
    public String listAll(Model model){
        ZSetOperations<String, String> operations = redisTemplate.opsForZSet();
        Set<ZSetOperations.TypedTuple<String>> idol = operations.reverseRangeByScoreWithScores("idol", 0, 9999999 * 999999);
       // for (ZSetOperations.TypedTuple<String> i:idol) (int)i.getScore();
        model.addAttribute("zsetAll",idol);
        return "zsetAll";
    }

   @GetMapping("/zsetadd")
    public String add(){
        return "addzset";
    }

    @PostMapping("/addzset")
    public String addZset(String idol , String score,Model model){
        if(idol==""||score==""){ model.addAttribute("error","不能提交空名字！"); return "addzset";}
        ZSetOperations<String, String> operations = redisTemplate.opsForZSet();
       double dscore=Double.valueOf(score);
        operations.add("idol", idol,dscore);
        Set<String> idols = operations.reverseRangeByScore("idol", 0, 9999999*999999);
        //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        model.addAttribute("listAll",idols);
        //model.addAttribute("time",df.format(new Date()));
        return "redirect:/zsetAll";
    }
    @GetMapping("/call/{idol}")
    public String call(@PathVariable("idol")String idol, Model model){
        ZSetOperations<String, String> operations = redisTemplate.opsForZSet();
        operations.incrementScore("idol",idol,1);
        return "redirect:/zsetAll";
    }
    @GetMapping("/delidol/{idol}")
    public String delete(@PathVariable("idol")String idol, Model model){
        ZSetOperations<String, String> operations = redisTemplate.opsForZSet();
        operations.remove("idol",idol);
        return "redirect:/zsetAll";
    }
}
