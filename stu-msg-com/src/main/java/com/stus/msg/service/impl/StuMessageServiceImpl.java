package com.stus.msg.service.impl;

import com.stus.msg.entity.StuMessage;
import com.stus.msg.mapper.StuMessageMapper;
import com.stus.msg.service.StuMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 学生信息Service
 *
 * @author liujingfang
 * @date 2017/12/29
 */
@Service
public class StuMessageServiceImpl implements StuMessageService {

  @Autowired
  private StuMessageMapper stuMessageMapper;

  /**
   * 查询学生信息
   *
   * @param stuId 学生信息ID
   * @return
   */
  @Override
  public List<StuMessage> findStuMessage(Integer stuId) {
    List<StuMessage> stuMessageList = null;
    if (stuId != null && stuId > 0) {
      stuMessageList = stuMessageMapper.findStuById(stuId);
      stuMessageList.forEach(this::getClassName);
      return stuMessageList;
    }
    stuMessageList = stuMessageMapper.findAll();
    stuMessageList.forEach(this::getClassName);
    return stuMessageList;
  }

  /**
   * 获取班级名称
   *
   * @param stuMessage
   * @return
   */
  public StuMessage getClassName(StuMessage stuMessage) {
    String classNum = stuMessage.getClassNum();
    StringBuilder className = new StringBuilder();
    className.append(classNum.substring(0, 4)).append("q").append(name(classNum.substring(4, 8))).append(classNum.substring(8, 10)).append("班");
    stuMessage.setClassName(className.toString());
    return stuMessage;
  }
  private String name(String s) {
    if ("2443".equals(s)) {
      return "计算机";
    } else {
      return "";
    }
  }

}
