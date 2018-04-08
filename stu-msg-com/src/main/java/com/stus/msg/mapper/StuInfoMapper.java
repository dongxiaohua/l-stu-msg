package com.stus.msg.mapper;

import com.stus.msg.entity.StuInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author liujingfang
 * @date 2017/12/29
 */
public interface StuInfoMapper {

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
  @Select("SELECT * FROM stu_info WHERE stu_number = #{stuNumber}")
  List<StuInfo> getInfoList(@Param("stuNumber") String stuNumber);

}
