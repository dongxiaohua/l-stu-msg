package com.stus.msg.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 *  班级荣誉信息表
 * @author liujingfang
 * @date 2017/12/29
 */
@Getter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassInfo {
  private int id;
  private String classNum;
  private Date theTime;
  private String classContent;
}
