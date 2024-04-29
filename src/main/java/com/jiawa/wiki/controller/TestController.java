package com.jiawa.wiki.controller;

import com.jiawa.wiki.domain.Test;
import com.jiawa.wiki.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class TestController {

    private static final Logger LOG = LoggerFactory.getLogger(TestController.class);

    @Resource
    private TestService testService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    @GetMapping("/test/list")
    public List<Test> list() {
        return testService.list();
    }

    @GetMapping("/redis/set/{key}/{value}")
    public String set(@PathVariable String key, @PathVariable String value) {
        stringRedisTemplate.opsForValue().set(key, value, 3600, TimeUnit.SECONDS);
        LOG.info("key: {}, value: {}", key, value);
        return "success";
    }

    @GetMapping("/redis/get/{key}")
    public Object get(@PathVariable Long key) {
        Object object = stringRedisTemplate.opsForValue().get(String.valueOf(key));
        LOG.info("key: {}, object: {}", key, object);
        return object;
    }

}
