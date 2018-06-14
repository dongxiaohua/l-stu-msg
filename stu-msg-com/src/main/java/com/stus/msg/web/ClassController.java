package com.stus.msg.web;

import com.alibaba.fastjson.JSONObject;
import com.stus.msg.entity.ClassInfo;
import com.stus.msg.entity.StuClass;
import com.stus.msg.mapper.ClassInfoMapper;
import com.stus.msg.mapper.StuClassMapper;
import com.stus.msg.service.StuClassService;
import com.stus.msg.util.HttpResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author dongxiaohua
 * Created on 2018/4/17.
 */
@RestController
@Slf4j
@RequestMapping("/class")
public class ClassController {

  @Autowired
  private StuClassService stuClassService;
  @Autowired
  private StuClassMapper stuClassMapper;
  @Autowired
  private ClassInfoMapper classInfoMapper;


  /**
   * 获取class信息列表
   *
   * @param classNum
   * @return
   */
  @RequestMapping(value = "/getClassMessageList", method = RequestMethod.GET)
  public List<StuClass> getClassMessageList(@RequestParam(required = false) String classNum) {
    return stuClassService.getClassMessageList(classNum);
  }

  /**
   * 添加班级信息
   *
   * @param stuClass
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public HttpResult add(@RequestBody StuClass stuClass, HttpSession session) {
    String right = String.valueOf(session.getAttribute("rights"));
    HttpResult res = new HttpResult();
    if ("stu".equals(right) || "null".equals(right)) {
      res.fail();
      res.setMsg("对不起，你没有权限！");
      return res;
    }
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
   * 编辑班级信息
   *
   * @param stuClass
   * @return
   */
  @RequestMapping(value = "/edit", method = RequestMethod.POST)
  public HttpResult edit(@RequestBody StuClass stuClass, HttpSession session) {
    String right = String.valueOf(session.getAttribute("rights"));
    HttpResult res = new HttpResult();
    if ("stu".equals(right) || "null".equals(right)) {
      res.fail();
      res.setMsg("对不起，你没有权限！");
      return res;
    }
    try {
      stuClassMapper.update(stuClass);
      res.success();
      res.setMsg("编辑成功");
    } catch (Exception e) {
      res.fail();
      res.setMsg("编辑异常");
    }
    return res;
  }

  /**
   * 添加班级后勤信息
   *
   * @return
   */
  @RequestMapping(value = "/add-info", method = RequestMethod.POST)
  public HttpResult addInfo(@RequestBody ClassInfo classInfo, HttpSession session) {
    String right = String.valueOf(session.getAttribute("rights"));
    HttpResult res = new HttpResult();
    if ("stu".equals(right) || "null".equals(right)) {
      res.fail();
      res.setMsg("对不起，你没有权限！");
      return res;
    }
    try {
      classInfoMapper.insert(classInfo);
      res.success();
      res.setMsg("添加成功");
    } catch (Exception e) {
      res.fail();
      res.setMsg("添加异常");
    }
    return res;
  }

  /**
   * 获取后勤信息
   *
   * @return
   */
  @RequestMapping(value = "/get-class-info", method = RequestMethod.POST)
  public HttpResult getInfo(@RequestBody String dataObject) {
    JSONObject jsonObject = JSONObject.parseObject(dataObject);
    HttpResult res = new HttpResult();
    try {
      res.success();
      res.setData(classInfoMapper.findClassInfoByClassNum(jsonObject.getString("classNum")));
    } catch (Exception e) {
      res.fail();
      res.setMsg("查询异常");
    }
    return res;
  }
}
