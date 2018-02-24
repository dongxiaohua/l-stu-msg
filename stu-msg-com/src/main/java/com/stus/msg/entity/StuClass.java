package com.stus.msg.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *  班级表
 * @author liujingfang
 * @date 2017/12/29
 */
@Getter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StuClass {
  private int id;
  private String className;
  private String classDepartment;
  private String classGrade;
  private String classNum;
}
