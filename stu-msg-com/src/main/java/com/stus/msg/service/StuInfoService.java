package com.stus.msg.service;

import com.stus.msg.entity.StuInfo;

import java.util.List;

/**
 * @author liujingfang
 * @date 2017/12/29
 */
public interface StuInfoService {

  /**
   * 插入info
   *
   * @param stuInfo
   * @return
   */
  int insert(StuInfo stuInfo);


  /**
   * 根据学号查询后勤信息
   *
   * @param stuNumber
   * @return
   */
  List<StuInfo> getInfoList(String stuNumber);

  /**
   * 修改学生后勤信息
   *
   * @param stuInfo
   * @return
   */
  Integer editStuInfo(StuInfo stuInfo);

}
