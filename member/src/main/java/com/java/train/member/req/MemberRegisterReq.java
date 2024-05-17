package com.java.train.member.req;

import jakarta.validation.constraints.NotBlank;

/**
 * @author Tsk
 * @date 2024/5/14 0014
 */

public class MemberRegisterReq {

    @NotBlank(message = "手机号不能为空")
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "MemberRegisterReq{" +
                "mobile='" + mobile + '\'' +
                '}';
    }
}
