package com.stus.msg.web;

import com.stus.msg.entity.LoginUser;
import com.stus.msg.entity.User;
import com.stus.msg.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by dongxiaohua on 2017/12/22.
 */
@Controller
@Slf4j
public class LoginController {
  @Autowired
  private UserMapper userMapper;

  @RequestMapping(value = "/login",method = RequestMethod.POST)
  @ResponseBody
  public LoginUser login(@RequestBody User user){
    User u = userMapper.find(user.getUsername(),user.getPassword());
    if (u != null){
      return LoginUser.builder().msg("登录成功").code(200).user(u).build();
    } else {
      return LoginUser.builder().msg("登录失败").code(400).user(null).build();
    }

  }





}
