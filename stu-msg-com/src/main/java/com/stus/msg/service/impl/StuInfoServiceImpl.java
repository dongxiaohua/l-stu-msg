package com.stus.msg.service.impl;

import com.stus.msg.entity.StuInfo;
import com.stus.msg.mapper.StuInfoMapper;
import com.stus.msg.service.StuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liujingfang
 * @date 2017/12/29
 */
@Service
public class StuInfoServiceImpl implements StuInfoService {

  @Autowired
  private StuInfoMapper stuInfoMapper;

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
}
