package com.stus.msg.service.impl;

import com.google.common.base.Strings;
import com.stus.msg.entity.StuUser;
import com.stus.msg.mapper.StuUserMapper;
import com.stus.msg.service.StuUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liujingfang
 * @date 2017/12/22
 */
@Service
@Slf4j
public class StuUserServiceImpl implements StuUserService {

  @Autowired
  private StuUserMapper stuUserMapper;

  /**
   * 查询用户信息
   *
   * @return
   */
  @Override
  public List<StuUser> findAllUser(String userName) {
    List<StuUser> stuUsers;
    if (Strings.isNullOrEmpty(userName)) {
      stuUsers = stuUserMapper.findAll();
    } else {
      stuUsers = stuUserMapper.findByName(userName);
    }
    return stuUsers;
  }

  /**
   * 添加用户
   *
   * @return
   */
  @Override
  public Integer add(StuUser stuUser) {
    List<StuUser> stuUsers = stuUserMapper.findByName(stuUser.getUserName());
    if (stuUsers.isEmpty()) {
      return 0;
    }
    return stuUserMapper.insert(stuUser);
  }
}
