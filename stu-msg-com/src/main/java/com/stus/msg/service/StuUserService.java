package com.stus.msg.service;

import com.stus.msg.entity.StuUser;

import java.util.List;

/**
 * @author dongxiaohua
 * @date 2017/12/22
 */
public interface StuUserService {

    /**
     * 查询所有用户信息
     *
     * @return
     */
    List<StuUser> findAllUser();
}
