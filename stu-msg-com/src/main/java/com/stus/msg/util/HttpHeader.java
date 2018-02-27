package com.stus.msg.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * *
 * Created by liujingfang on 2017/9/11.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HttpHeader {
  private String name;
  private String value;
  private String matchMode;
}
