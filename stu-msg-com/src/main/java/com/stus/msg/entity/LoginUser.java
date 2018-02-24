package com.stus.msg.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *
 * @author liujingfang
 * @date 2017/12/22
 */
@Getter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser {
  private String msg;
  private int code;
  private StuUser user;
}
