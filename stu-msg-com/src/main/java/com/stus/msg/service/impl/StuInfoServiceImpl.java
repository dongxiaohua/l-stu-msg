package com.stus.msg.service.impl;

import com.stus.msg.entity.StuInfo;
import com.stus.msg.mapper.StuInfoMapper;
import com.stus.msg.mapper.StuMessageMapper;
import com.stus.msg.service.StuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liujingfang
 * @date 2017/12/29
 */
@Service
public class StuInfoServiceImpl implements StuInfoService {

  @Autowired
  private StuInfoMapper stuInfoMapper;
  @Autowired
  private StuMessageMapper stuMessageMapper;

  /**
   * 插入info
   *
   * @param stuInfo
   * @return
   */
  @Override
  public int insert(StuInfo stuInfo) {
    return stuInfoMapper.insert(stuInfo);
  }

  /**
   * 根据学号查询后勤信息
   *
   * @param stuNumber
   * @return
   */
  @Override
  public List<StuInfo> getInfoList(String stuNumber) {
    List<StuInfo> stuInfoList = stuInfoMapper.getInfoList(stuNumber);
    String stuName = stuMessageMapper.findStuNameByNumber(stuNumber);
    stuInfoList.forEach(info -> {
      info.setStuName(stuName);
    });
    return stuInfoList;
  }

  /**
   * 编辑学生后勤信息
   *
   * @param stuInfo
   * @return
   */
  @Override
  public Integer editStuInfo(StuInfo stuInfo) {
    return stuInfoMapper.edit(stuInfo);
  }
}
