package com.stus.msg.service;

import com.stus.msg.entity.StuClass;

import java.util.List;

/**
 * @author liujingfang
 * @date 2017/12/29
 */
public interface StuClassService {

  /**
   * 查询班级列表
   *
   * @param classNum
   * @return
   */
  List<StuClass> getClassMessageList(String classNum);

  /**
   * 添加班级
   *
   * @param stuClass
   * @return
   */
  Integer add(StuClass stuClass);

}
