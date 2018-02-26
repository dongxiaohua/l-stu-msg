package com.stus.msg.service;

import com.stus.msg.entity.StuMessage;

import java.util.List;

/**
 *
 * @author liujingfang
 * @date 2017/12/29
 */
public interface StuMessageService {

  /**
   * 查询学生信息
   * @param stuId
   * @return
   */
  List<StuMessage> findStuMessage(Integer stuId);
}
