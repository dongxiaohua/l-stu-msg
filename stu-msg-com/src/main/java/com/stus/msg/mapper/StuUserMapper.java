package com.stus.msg.mapper;

import com.stus.msg.entity.StuUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 *
 * @author liujingfang
 * @date 2017/12/22
 */
public interface StuUserMapper {

  /**
   * 查询用户名密码是否正确
   * @param name
   * @param pwd
   * @return
   */
  @Select("SELECT * FROM stu_user WHERE user_name = #{userName} AND pass_word = #{pwd}")
  StuUser find(@Param("userName")String name, @Param("pwd")String pwd);
}
