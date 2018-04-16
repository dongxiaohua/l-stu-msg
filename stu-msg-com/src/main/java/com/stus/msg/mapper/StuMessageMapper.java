package com.stus.msg.mapper;

import com.stus.msg.entity.StuMessage;
import org.apache.ibatis.annotations.Delete;
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
   *
   * @param stuNumber
   * @return
   */
  @Select("SELECT * FROM stu_message WHERE stu_number = #{stuNumber}")
  List<StuMessage> findStuById(@Param("stuNumber") String stuNumber);

  /**
   * 根据学号查姓名
   *
   * @param stuNumber
   * @return
   */
  @Select("SELECT stu_name FROM stu_message WHERE stu_number = #{stuNumber}")
  String findStuNameByNumber(@Param("stuNumber") String stuNumber);

  /**
   * 根据id删除
   *
   * @param stuNumber
   * @return
   */
  @Delete("DELETE FROM stu_message WHERE stu_number = #{stuNumber}")
  Integer deleteById(@Param("stuNumber") String stuNumber);

  /**
   * 插入新数据
   *
   * @param stuMessage
   * @return
   */
  Integer insert(StuMessage stuMessage);

  /**
   * 编辑信息
   *
   * @param stuMessage
   * @return
   */
  Integer edit(StuMessage stuMessage);

}
