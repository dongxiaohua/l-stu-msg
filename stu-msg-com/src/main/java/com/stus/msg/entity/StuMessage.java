package com.stus.msg.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;

/**
 *  学生个人信息表
 * @author liujingfang
 * @date 2017/12/29
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StuMessage {
  private int id;
  private String stuName;
  private Integer stuSex;
  private Integer stuAge;
  private String stuNumber;
  private String classNum;
  @Transient
  private String className;
}
