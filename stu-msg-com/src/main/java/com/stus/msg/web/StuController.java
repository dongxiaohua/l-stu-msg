package com.stus.msg.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author liujingfang
 *         Created on 2018/2/24.
 */
@Controller
@RequestMapping("/stu")
public class StuController {

  @RequestMapping(value = "/list",method = RequestMethod.GET)
  public String listGet(){

    return "";
  }

}
