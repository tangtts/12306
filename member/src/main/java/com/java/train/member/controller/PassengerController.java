package com.java.train.member.controller;

import com.java.train.common.context.LoginMemberContext;
import com.java.train.common.resp.CommonResp;
import com.java.train.common.resp.PageResp;
import com.java.train.member.req.PassengerQueryReq;
import com.java.train.member.req.PassengerSaveReq;
import com.java.train.member.resp.PassengerQueryResp;
import com.java.train.member.service.PassengerService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * @author Tsk
 * @date 2024/5/16 0016
 */

@RestController
@RequestMapping("/passenger")
public class PassengerController {
    @Resource
    private PassengerService passengerService;

    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody PassengerSaveReq passenger)
    {
        passengerService.save(passenger);
        return new CommonResp<>();
    }

    @GetMapping("/query-list")
    // get 请求会自动映射到 类
    public CommonResp<PageResp<PassengerQueryResp>> query(@Valid PassengerQueryReq req)
    {
        req.setMemberId(LoginMemberContext.getId());
        PageResp<PassengerQueryResp> list = passengerService.queryList(req);
        return new CommonResp<>(list);
    }

    @GetMapping("/delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id)
    {
        passengerService.delete(id);
        return new CommonResp<>();
    }
}
