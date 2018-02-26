package com.stus.msg.web;

import com.google.common.collect.Maps;
import com.stus.msg.entity.StuMessage;
import com.stus.msg.mapper.StuMessageMapper;
import com.stus.msg.service.StuMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 学生模块
 *
 * @author liujingfang
 *         Created on 2018/2/24.
 */
@RestController
@RequestMapping("/stu")
public class StuController {

  @Autowired
  private StuMessageService stuMessageService;

  //required = false 代表如果不传，参数为null
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public List<StuMessage> listGet(@RequestParam(required = false) Integer stuId) {
    List<StuMessage> stuMessageList = stuMessageService.findStuMessage(stuId);
    return stuMessageList;
  }

}
