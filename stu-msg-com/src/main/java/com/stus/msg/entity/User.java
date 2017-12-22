package com.stus.msg.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by dongxiaohua on 2017/12/22.
 */
@Getter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
  private int id;
  private String username;
  private String password;

}
