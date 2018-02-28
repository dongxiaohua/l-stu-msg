package com.stus.msg.web;

import com.stus.msg.entity.StuInfo;
import com.stus.msg.service.ClassInfoService;
import com.stus.msg.service.StuInfoService;
import com.stus.msg.util.HttpResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

  @Autowired
  private StuInfoService stuInfoService;
  @Autowired
  private ClassInfoService classInfoService;

  /**
   * 添加学生info
   *
   * @return
   */
  @RequestMapping(value = "/add-stu", method = RequestMethod.POST)
  public HttpResult addStuInfo(@RequestBody StuInfo stuInfo) {
    HttpResult res = new HttpResult();
    try {
      int i = stuInfoService.insert(stuInfo);
      if (i == 1) {
        return res.success();
      } else {
        log.error("插入info失败");
        return res.fail();
      }
    } catch (Exception e) {
      log.error("插入info异常:",e);
      return res.fail();
    }
  }




}
