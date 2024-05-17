package com.java.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.train.common.context.LoginMemberContext;
import com.java.train.common.resp.PageResp;
import com.java.train.common.util.SnowUtil;
import com.java.train.member.domain.Passenger;
import com.java.train.member.domain.PassengerExample;
import com.java.train.member.mapper.PassengerMapper;
import com.java.train.member.req.PassengerQueryReq;
import com.java.train.member.req.PassengerSaveReq;
import com.java.train.member.resp.PassengerQueryResp;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tsk
 * @date 2024/5/16 0016
 */
@Service
public class PassengerService {
    @Resource
    private PassengerMapper passengerMapper;

    public PageResp<PassengerQueryResp> queryList(PassengerQueryReq req) {
        PassengerExample passengerExample = new PassengerExample();
        // ctrl + alt + v 提取变量
        // shift + f6
        if (ObjectUtil.isNull(req.getMemberId())) {
            PassengerExample.Criteria criteria = passengerExample.createCriteria();
            criteria.andMemberIdEqualTo(req.getMemberId());
        }
        System.out.println(req.getPage());
        PageHelper.startPage(req.getPage(), req.getSize());
        List<Passenger> passengers = passengerMapper.selectByExample(passengerExample);
        PageInfo<Passenger> pageInfo = new PageInfo<>(passengers);

        List<PassengerQueryResp> list = BeanUtil.copyToList(passengers, PassengerQueryResp.class);
        PageResp<PassengerQueryResp> pageResp = new PageResp<>();
        pageResp.setList(list);
        pageResp.setTotal(pageInfo.getTotal());
        return pageResp;
    }


    public void save(PassengerSaveReq req) {
        DateTime now = DateTime.now();
        Passenger passenger = BeanUtil.copyProperties(req, Passenger.class);
        if (ObjectUtil.isNull(req.getId())) {
            passenger.setMemberId(LoginMemberContext.getId());
            passenger.setId(SnowUtil.getSnowflakeNextId());
            passenger.setCreateTime(now);
            passenger.setUpdateTime(now);
            passengerMapper.insert(passenger);
        } else {
            passenger.setUpdateTime(now);
            passengerMapper.updateByPrimaryKey(passenger);
        }
    }

    public void delete(Long id) {
        passengerMapper.deleteByPrimaryKey(id);
    }
}
