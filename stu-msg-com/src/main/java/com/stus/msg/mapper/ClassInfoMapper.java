package com.stus.msg.mapper;

import com.stus.msg.entity.ClassInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author liujingfang
 * @date 2017/12/29
 */
public interface ClassInfoMapper {

  /**
   * 插入班级后勤信息
   *
   * @param classInfo
   * @return
   */
  Integer insert(ClassInfo classInfo);

  /**
   * 查询班级后勤信息
   *
   * @param classNum
   * @return
   */
  @Select("SELECT * FROM class_info WHERE class_num = #{classNum}")
  List<ClassInfo> findClassInfoByClassNum(@Param("classNum") String classNum);

}
