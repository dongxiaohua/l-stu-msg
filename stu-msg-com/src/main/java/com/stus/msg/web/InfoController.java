package com.stus.msg.web;

import com.alibaba.fastjson.JSONObject;
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

import java.util.List;

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
      log.error("插入info异常:", e);
      return res.fail();
    }
  }

  /**
   * 根据学号查询学生后勤信息
   *
   * @param dataObject
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public HttpResult list(@RequestBody String dataObject) {
    JSONObject jsonObject = JSONObject.parseObject(dataObject);
    HttpResult res = new HttpResult();
    try {
      List<StuInfo> stuInfoList = stuInfoService.getInfoList(jsonObject.getString("stuNumber"));
      res.success();
      res.setData(stuInfoList);
    } catch (Exception e) {
      log.error("查询学生后勤信息异常，error:", e);
      res.fail();
      res.setMsg(e.getMessage());
    }
    return res;
  }

  /**
   * 编辑
   * @param stuInfo
   * @return
   */
  @RequestMapping(value = "/edit", method = RequestMethod.POST)
  public HttpResult edit(@RequestBody StuInfo stuInfo) {
    HttpResult res = new HttpResult();
    try {
      Integer i = stuInfoService.editStuInfo(stuInfo);
      if (i > 0) {
        res.success();
      } else {
        res.fail();
        log.error("编辑失败");
      }
    } catch (Exception e) {
      log.error("修改学生后勤信息异常，error:", e);
      res.fail();
      res.setMsg(e.getMessage());
    }
    return res;
  }
}