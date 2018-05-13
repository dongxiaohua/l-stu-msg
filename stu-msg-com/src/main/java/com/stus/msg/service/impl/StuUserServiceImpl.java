package com.stus.msg.service.impl;

import com.stus.msg.entity.StuUser;
import com.stus.msg.mapper.StuUserMapper;
import com.stus.msg.service.StuUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author liujingfang
 * @date 2017/12/22
 */
@Service
public class StuUserServiceImpl implements StuUserService {

    @Autowired
    private StuUserMapper stuUserMapper;

    /**
     * 查询所有用户信息
     * @return
     */
    @Override
    public List<StuUser> findAllUser() {
        return stuUserMapper.findAll();
    }
}
