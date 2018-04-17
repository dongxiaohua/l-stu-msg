package com.stus.msg.mapper;

import com.stus.msg.entity.StuClass;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author liujingfang
 * @date 2017/12/29
 */
public interface StuClassMapper {

  /**
   * 获取指定班级编号的班级信息
   *
   * @param classNum
   * @return
   */
  @Select("SELECT * FROM stu_class WHERE class_num = #{classNum}")
  List<StuClass> findClassByNum(@Param("classNum") String classNum);

  /**
   * 查询班级信息列表
   *
   * @return
   */
  @Select("SELECT * FROM stu_class")
  List<StuClass> findAll();

  /**
   * 添加班级
   *
   * @param stuClass
   * @return
   */
  Integer insert(StuClass stuClass);

}
