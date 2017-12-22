package com.stus.msg.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by dongxiaohua on 2017/12/22.
 */
public interface UserMapper {

  @Select("SELECT * FROM stu_user WHERE username = {#name} AND password = {#pwd}")
  int findAll(@Param("name")String name,@Param("pwd")String pwd);
}
