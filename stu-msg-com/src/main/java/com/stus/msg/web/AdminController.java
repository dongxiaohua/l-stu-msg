package com.stus.msg.web;

import com.stus.msg.entity.StuUser;
import com.stus.msg.service.StuUserService;
import com.stus.msg.util.HttpResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author dongxiaohua
 * @date 2018/5/13
 */
@RestController
@Slf4j
@RequestMapping(value = "/admin")
public class AdminController {

  @Autowired
  private StuUserService stuUserService;

  /**
   * 获取所有用户
   *
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public List<StuUser> list(@RequestParam(required = false) String userName) {
    return stuUserService.findAllUser(userName);
  }

  /**
   * 添加用户
   *
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public HttpResult add(@RequestBody StuUser stuUser) {
    HttpResult res = new HttpResult();
    try {
      if (stuUserService.add(stuUser) > 0) {
        res.success();
      } else {
        res.fail();
        res.setMsg("用户名已存在");
      }
    } catch (Exception e) {
      log.error("添加用户异常，error:", e);
      res.fail();
      res.setMsg("添加用户异常");
    }
    return res;
  }

}
