package com.stus.msg.util;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Http返回结果的JSONF封装
 * Created by liujingfang on 2017/9/6.
 */
@Getter
@Setter
@Accessors(chain = true)
public class HttpResult {

  public static final String SUCCESS = "SUCCESS";
  public static final String FAIL = "FAIL";
  public static final String WARN = "WARN";


  private String msg = "";
  private String code = WARN;
  private Object data;

  public static HttpResult instance() {
    return new HttpResult();
  }

  @Override
  public String toString() {
    return "CommonResult{" + "msg='" + msg + '\'' + ", code=" + code + '}';
  }

  public String toJson() {
    return JSONObject.toJSONString(this);
  }

  public HttpResult success() {
    return this.setCode(SUCCESS);
  }

  public HttpResult fail() {
    return this.setCode(FAIL);
  }

  public HttpResult warn() {
    return this.setCode(WARN);
  }

}
