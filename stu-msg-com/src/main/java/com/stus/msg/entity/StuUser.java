package com.stus.msg.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * stu_user表
 *
 * @author liujingfang
 * @date 2017/12/22
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StuUser {
  private Integer id;
  private String userName;
  private String passWord;
  private String rights;
  private Integer status;
  private Date loginTime;
}
