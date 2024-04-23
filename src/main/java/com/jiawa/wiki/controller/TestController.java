package com.jiawa.wiki.controller;

import com.jiawa.wiki.domain.Test;
import com.jiawa.wiki.service.TestService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TestController {



    @Resource
    private TestService testService;


    @GetMapping("/test/list")
    public List<Test> list() {
        return testService.list();
    }

}
