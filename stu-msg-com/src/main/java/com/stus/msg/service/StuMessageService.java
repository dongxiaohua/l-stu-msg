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
   * @param stuNumber
   * @return
   */
  List<StuMessage> findStuMessage(String stuNumber);

  /**
   * 根据学号删除
   * @param stuNumber
   * @return
   */
  int deleteById(String stuNumber);

  /**
   * 插入新数据
   * @param stuMessage
   * @return
   */
  int insert(StuMessage stuMessage);

  /**
   * 编辑数据
   * @param stuMessage
   * @return
   */
  int edit(StuMessage stuMessage);
}
