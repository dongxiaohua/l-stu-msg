package com.stus.msg.util;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author dongxiaohua
 *         Created on 2018/4/8.
 */
@Slf4j
class DateFormatUtil {
  private static final SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd");

  /**
   * long型转字符串型
   *
   * @param date
   * @return
   */
  static String formatLong(Long date) {
    return dtf.format(date);
  }

  /**
   * 字符串型转long型
   *
   * @param date
   * @return
   */
  static Date formatString(String date) throws ParseException {
    return dtf.parse(date);
  }

}

