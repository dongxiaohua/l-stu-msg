package com.stuts.msg.ServiceTest

import com.stus.msg.entity.StuMessage
import com.stus.msg.service.StuMessageService
import com.stus.msg.service.StuUserService
import com.stus.msg.service.impl.StuMessageServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

/**
 * Created by liujingfang on 2017/12/28.
 */
@ContextConfiguration(locations = "classpath:mapperContext.xml")
class StuMessageServiceImplTest extends  Specification{
  @Autowired
  StuMessageServiceImpl stuMessageService

  def "getClassName-test" () {
    given:
    def classNum = "2014244302"
    def stuMessage = new StuMessage()
    stuMessage.setClassNum(classNum)
    expect:
    println "===========" +stuMessageService.getClassName(stuMessage).getClassName()
  }

}
