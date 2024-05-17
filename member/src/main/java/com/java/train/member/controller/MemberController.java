package com.java.train.member.controller;

import com.java.train.common.resp.CommonResp;
import com.java.train.member.req.MemberLoginReq;
import com.java.train.member.req.MemberRegisterReq;
import com.java.train.member.resp.MemberLoginResp;
import com.java.train.member.service.MemberService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tsk
 * @date 2024/5/6 0006
 */
@RestController
@RequestMapping("/member")
public class MemberController {
    @Resource
    private MemberService memberService;

    @GetMapping("/hello")
    public String test() {
        return "test";
    }

    @GetMapping("/count")
    public CommonResp<Integer> count() {
        CommonResp<Integer> commonResp = new CommonResp<>();
        commonResp.setContent(memberService.count());
        return commonResp;
    }

    @PostMapping("/register")
    public CommonResp<Long> register(@Valid MemberRegisterReq req) {
        CommonResp<Long> commonResp = new CommonResp<>();
        commonResp.setContent(memberService.register(req));
        return commonResp;
    }

    @PostMapping("/login")
    public CommonResp<MemberLoginResp> login(@Valid MemberLoginReq req) {
        MemberLoginResp resp = memberService.login(req);
        return new CommonResp<>(resp);
    }

}