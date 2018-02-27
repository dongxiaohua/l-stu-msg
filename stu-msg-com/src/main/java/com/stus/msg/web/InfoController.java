package com.stus.msg.web;

import com.stus.msg.entity.StuInfo;
import com.stus.msg.util.HttpResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dongxiaohua
 *         Created on 2018/2/27.
 */
@RestController
@RequestMapping("/info")
@Slf4j
public class InfoController {

  /**
   * 添加学生info
   * @return
   */
  @RequestMapping(value = "/add-stu", method = RequestMethod.GET)
  public HttpResult addStuInfo(@RequestBody StuInfo stuInfo) {
    HttpResult res = new HttpResult();

    return res.success();
  }

}
