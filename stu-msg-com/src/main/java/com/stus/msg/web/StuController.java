package com.stus.msg.web;

import com.stus.msg.entity.StuMessage;
import com.stus.msg.service.StuMessageService;
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
 * 学生模块
 *
 * @author liujingfang
 *         Created on 2018/2/24.
 */
@RestController
@RequestMapping("/stu")
@Slf4j
public class StuController {

  @Autowired
  private StuMessageService stuMessageService;

  /**
   * 获取信息列表
   *
   * @param stuNumber
   * @return
   */
  //required = false 代表如果不传，参数为null
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public List<StuMessage> listGet(@RequestParam(required = false) String stuNumber) {
    return stuMessageService.findStuMessage(stuNumber);
  }


  /**
   * 根据学号删除
   *
   * @param stuNumber
   * @return
   */
  @RequestMapping(value = "/remove", method = RequestMethod.GET)
  public HttpResult remove(@RequestParam String stuNumber) {
    HttpResult res = new HttpResult();
    stuMessageService.deleteById(stuNumber);
    return res.success();
  }

  /**
   * 添加信息
   *
   * @param stuMessage
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public HttpResult add(@RequestBody StuMessage stuMessage) {
    HttpResult res = new HttpResult();
    try {
      int i = stuMessageService.insert(stuMessage);
      if (i == 1) {
        return res.success();
      } else {
        log.error("error", "插入失败");
        return res.fail();
      }
    } catch (Exception e) {
      log.error("error:", "插入异常", e);
      return res.fail();
    }
  }

  /**
   * 编辑信息
   *
   * @param stuMessage
   * @return
   */
  @RequestMapping(value = "/edit", method = RequestMethod.POST)
  public HttpResult edit(@RequestBody StuMessage stuMessage) {
    HttpResult res = new HttpResult();
    try {
      int i = stuMessageService.edit(stuMessage);
      if (i == 1) {
        res.success();
      } else {
        log.error("编辑失败，id={}", stuMessage.getId());
        res.fail();
      }
    } catch (Exception e) {
      log.error("error:", "修改异常", e);
      res.fail();
    }
    return res;
  }


  /**
   * 批量删除信息
   *
   * @return
   */
  @RequestMapping(value = "/batchremove", method = RequestMethod.POST)
  public HttpResult batchRemove() {
    HttpResult res = new HttpResult();

    return res.success();
  }

}
