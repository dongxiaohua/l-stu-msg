package com.stus.msg.mapper;

import com.stus.msg.entity.StuMessage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author liujingfang
 * @date 2017/12/29
 */
public interface StuMessageMapper {

  /**
   * 查询所有学生信息
   *
   * @return
   */
  @Select("SELECT * FROM stu_message")
  List<StuMessage> findAll();

  /**
   * 查询指定学生信息
   * @param stuId
   * @return
   */
  @Select("SELECT * FROM stu_message WHERE id = #{stuId}")
  List<StuMessage> findStuById(@Param("stuId") Integer stuId);

}
