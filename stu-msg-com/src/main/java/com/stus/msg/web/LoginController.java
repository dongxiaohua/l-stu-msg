package com.stus.msg.web;

import com.stus.msg.entity.LoginUser;
import com.stus.msg.entity.StuUser;
import com.stus.msg.mapper.StuUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author liujingfang
 * @date 2017/12/22
 */
@Controller
@Slf4j
public class LoginController {

  @Autowired
  private StuUserMapper userMapper;

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  @ResponseBody
  public LoginUser login(@RequestBody StuUser user, HttpSession session) {
    StuUser u = userMapper.find(user.getUserName(), user.getPassWord());
    if (u != null) {
      session.setAttribute("userId", u.getId());
      session.setAttribute("rights", u.getRights());
      return LoginUser.builder().msg("登录成功").code(200).user(u).build();
    } else {
      return LoginUser.builder().msg("登录失败").code(400).user(null).build();
    }

  }

  @RequestMapping(value = "/")
  public String home() {
    return "index";
  }


}
