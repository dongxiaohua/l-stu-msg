package com.stuts.msg.ServiceTest

import com.stus.msg.service.StuUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

/**
 * Created by dongxiaohua on 2017/12/28.
 */
@ContextConfiguration(locations = "classpath:mapperContext.xml")
class UserServiceTest extends  Specification{
  @Autowired
  StuUserService userService

  def "userService-find" () {

  }

}
