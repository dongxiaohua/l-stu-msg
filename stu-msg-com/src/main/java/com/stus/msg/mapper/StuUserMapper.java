package com.stus.msg.mapper;

import com.stus.msg.entity.StuUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author liujingfang
 * @date 2017/12/22
 */
public interface StuUserMapper {

  /**
   * 查询用户名密码是否正确
   *
   * @param name
   * @param pwd
   * @return
   */
  @Select("SELECT * FROM stu_user WHERE user_name = #{userName} AND pass_word = #{pwd}")
  StuUser find(@Param("userName") String name, @Param("pwd") String pwd);

  /**
   * 查询所有用户信息
   *
   * @return
   */
  @Select("SELECT * FROM stu_user")
  List<StuUser> findAll();

  /**
   * 根据用户名查询
   *
   * @param userName 用户名
   * @return
   */
  @Select("SELECT * FROM stu_user WHERE user_name = #{userName}")
  List<StuUser> findByName(@Param("userName") String userName);

  /**
   * 添加用户
   *
   * @param stuUser
   * @return
   */
  @Insert("INSERT INTO stu_user (user_name,pass_word,rights) VALUE (#{stuUser.userName},#{stuUser.passWord},#{stuUser.rights})")
  Integer insert(@Param("stuUser") StuUser stuUser);

}
