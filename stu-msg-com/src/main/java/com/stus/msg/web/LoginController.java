package com.stus.msg.web;

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
  public int login(@RequestBody User user){
    int code = userMapper.findAll(user.getUsername(),user.getPassword());
//    int code = 200;
    if (code == 200){
      return 200;
    } else {
      return 400;
    }

  }




}
