package com.test2.demo.Controller;

import com.test2.demo.Po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisController {


    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @GetMapping("/test")
    public String index(){
        User user = new User();
        user.setId((long) 1);
        user.setUsername("nike");
        stringRedisTemplate.opsForValue().set("1","鲨鱼辣椒");
        return  stringRedisTemplate.opsForValue().get("1");
    }
}
