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
   * @param stuNumber 学生信息ID
   * @return
   */
  @Override
  public List<StuMessage> findStuMessage(String stuNumber) {
    List<StuMessage> stuMessageList = null;
    if (stuNumber != null && !"".equals(stuNumber)) {
      stuMessageList = stuMessageMapper.findStuById(stuNumber);
      stuMessageList.forEach(this::getClassName);
      return stuMessageList;
    }
    stuMessageList = stuMessageMapper.findAll();
    stuMessageList.forEach(this::getClassName);
    return stuMessageList;
  }

  /**
   * 根据学号删除
   *
   * @param stuNumber
   * @return
   */
  @Override
  public int deleteById(String stuNumber) {
    return stuMessageMapper.deleteById(stuNumber);
  }

  /**
   * 插入新数据
   *
   * @param stuMessage
   * @return
   */
  @Override
  public int insert(StuMessage stuMessage) {
    return stuMessageMapper.insert(stuMessage);
  }

  /**
   * 编辑数据
   * @param stuMessage
   * @return
   */
  @Override
  public int edit(StuMessage stuMessage) {
    return stuMessageMapper.edit(stuMessage);
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
