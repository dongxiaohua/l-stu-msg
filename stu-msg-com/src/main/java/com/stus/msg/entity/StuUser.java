package com.stus.msg.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * stu_user表
 * @author liujingfang
 * @date 2017/12/22
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StuUser {
  private int id;
  private String userName;
  private String passWord;
  private int limit;

}
