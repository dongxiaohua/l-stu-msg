package com.stus.msg.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 *  学生个人日常信息表
 * @author liujingfang
 * @date 2017/12/29
 */
@Getter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StuInfo {
  private Integer id;
  private String infoName;
  private String stuNumber;
  private Date theTime;
  private Date endTime;
  private String stuContent;
  private String stuReason;
  private Integer stuOff;
}
