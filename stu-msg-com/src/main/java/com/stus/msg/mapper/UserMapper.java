package com.stus.msg.mapper;

import com.stus.msg.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by dongxiaohua on 2017/12/22.
 */
public interface UserMapper {

  @Select("SELECT * FROM stu_user WHERE username = #{username} AND password = #{pwd}")
  User find(@Param("username")String name, @Param("pwd")String pwd);
}
