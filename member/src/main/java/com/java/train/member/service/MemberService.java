package com.java.train.member.service;

import com.java.train.member.domain.Member;
import com.java.train.member.domain.MemberExample;
import com.java.train.member.mapper.MemberMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author Tsk
 * @date 2024/5/10 0010
 */
@Service
public class MemberService {
    @Resource
    private MemberMapper memberMapper;

    public int count() {
        return Math.toIntExact(memberMapper.countByExample(null));
    }

    public long register(String mobile) {
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);

        if (memberMapper.countByExample(memberExample) > 0) {
            throw new RuntimeException("手机号已存在");
        }

        Member member = new Member();
        member.setId(System.currentTimeMillis());
        member.setMobile(mobile);
        memberMapper.insert(member);
        return member.getId();
    }
}
