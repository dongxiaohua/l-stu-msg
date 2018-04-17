package com.stus.msg.web;

import com.stus.msg.entity.StuClass;
import com.stus.msg.service.StuClassService;
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
 *         Created on 2018/4/17.
 */
@RestController
@Slf4j
@RequestMapping("/class")
public class ClassController {

  @Autowired
  private StuClassService stuClassService;


  /**
   * 获取class信息列表
   *
   * @param classNum
   * @return
   */
  @RequestMapping(value = "/getClassMessageList", method = RequestMethod.GET)
  public List<StuClass> getClassMessageList(@RequestParam(required = false) String classNum) {
    List<StuClass> stuClassList = stuClassService.getClassMessageList(classNum);
    return stuClassList;
  }

  /**
   * 添加班级信息
   *
   * @param stuClass
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public HttpResult add(@RequestBody StuClass stuClass) {
    HttpResult res = new HttpResult();
    try {
      Integer i = stuClassService.add(stuClass);
      //      Integer i = 1;
      if (i > 0) {
        res.success();
      } else {
        log.error("查询班级信息失败");
        res.fail();
      }
    } catch (Exception e) {
      log.error("创建班级信息异常，error:", e);
      res.fail();
    }
    return res;
  }

  /**
   * 添加班级后勤信息
   *
   * @return
   */
  @RequestMapping(value = "/add-info", method = RequestMethod.POST)
  public HttpResult addInfo() {
    HttpResult res = new HttpResult();

    return res;
  }
}
