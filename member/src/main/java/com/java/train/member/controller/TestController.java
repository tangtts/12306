package com.java.train.member.controller;

import com.java.train.member.service.MemberService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tsk
 * @date 2024/5/6 0006
 */
@RestController("/")
public class TestController {
    @Resource
    private MemberService memberService;

    @GetMapping("hello")
    public String test(){
        return "test";
    }

    @GetMapping("count")
    public int count(){
        return memberService.count();
    }

    @PostMapping("register")
    public long register(String mobile){
        return memberService.register(mobile);
    }
}
