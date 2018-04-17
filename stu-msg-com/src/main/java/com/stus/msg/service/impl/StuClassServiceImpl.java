package com.stus.msg.service.impl;

import com.google.common.base.Strings;
import com.stus.msg.entity.StuClass;
import com.stus.msg.mapper.StuClassMapper;
import com.stus.msg.service.StuClassService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liujingfang
 * @date 2017/12/29
 */
@Service
@Slf4j
public class StuClassServiceImpl implements StuClassService {

  @Autowired
  private StuClassMapper stuClassMapper;

  /**
   * 查询班级列表
   *
   * @param classNum
   * @return
   */
  @Override
  public List<StuClass> getClassMessageList(String classNum) {
    List<StuClass> stuClassList;
    if (Strings.isNullOrEmpty(classNum)) {
      stuClassList = stuClassMapper.findAll();
    } else {
      stuClassList = stuClassMapper.findClassByNum(classNum);
    }
    return stuClassList;
  }

  /**
   * 添加班级
   *
   * @param stuClass
   * @return
   */
  @Override
  public Integer add(StuClass stuClass) {
    return stuClassMapper.insert(stuClass);
  }

}
