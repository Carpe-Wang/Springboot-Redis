package com.CarpeWang.controller;

import com.CarpeWang.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @author wangkaipeng
 * controller控制器
 */
@RestController
public class StudentHandler {

    @Autowired
    private RedisTemplate redisTemplate;//这个是redis提供的一个包，使用这个对象，来操控redis


    @PostMapping("/set")//使用postMapping来存储学生对象
    public void set(@RequestBody Student student){
        //@RequestBody是将前端传来的json数据转化为Java对象，进行操作
        redisTemplate.opsForValue().set("Student",student);//获得ValueOperations<K,V>
    }

    @GetMapping("/get/{key}")
    public Student get(@PathVariable("key") String key){
        return (Student) redisTemplate.opsForValue().get(key);//数据类型强行转换
    }

    @DeleteMapping("/del/{ket}")
    public boolean del(@PathVariable("key")String key){
        redisTemplate.delete(key);
        return redisTemplate.hasKey(key);
    }

    /**
     * redis 五种数据类型
     * @return
     */
    @GetMapping("/string")
    public String StringTest(){//字符串
        redisTemplate.opsForValue().set("str","I am 973");
        String str= (String) redisTemplate.opsForValue().get("str");
        return str;
    }

    @GetMapping("/list")
    public List<String> ListTest(){//列表
       ListOperations<String,String> listOperations= redisTemplate.opsForList();
       listOperations.leftPush("List","The world");
       listOperations.leftPush("List","could you tell the reason");//注意左右
       List<String> list = listOperations.range("List", 0, 1);//第一个为key，第一个为开始截取的下标，第二个为结束
       return list;
    }

    @GetMapping("/zset")
    public Set<String> zsetTest(){//集合，set略
        ZSetOperations<String,String> zSetOperations=redisTemplate.opsForZSet();
        zSetOperations.add("zSet","hellp",1);
        zSetOperations.add("zSet","hell0123",2);
        zSetOperations.add("zSet","hell01234565",3);
        Set<String> set =zSetOperations.range("zSet",0,2);
        return set;
    }

}
