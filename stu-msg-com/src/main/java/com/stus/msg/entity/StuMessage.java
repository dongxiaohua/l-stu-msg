package com.stus.msg.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *  学生个人信息表
 * @author dongxiaohua
 * @date 2017/12/29
 */
@Getter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StuMessage {
  private int id;
  private String stuName;
  private String stuSex;
  private int stuAge;
  private long stuNumber;
  private String cNum;
}
