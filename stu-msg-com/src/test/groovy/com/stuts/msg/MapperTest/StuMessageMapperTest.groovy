package com.stuts.msg.MapperTest

import com.stus.msg.entity.StuMessage
import com.stus.msg.mapper.StuMessageMapper
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

/**
 * @author liujingfang
 *  Created on 2017/12/28.
 */
@ContextConfiguration(locations = "classpath:mapperContext.xml")
@Slf4j
class StuMessageMapperTest extends Specification {
  @Autowired
  StuMessageMapper stuMessageMapper

  def "insert-test"() {
    given:
    def stu = new StuMessage()
    stu.setStuName(name)
    stu.setStuSex(sex)
    stu.setStuAge(age)
    stu.setStuNumber(stuNum)
    stu.setClassNum(classNum)
    expect:
    println "==========" + stuMessageMapper.insert(stu)
    where:
    name   |   sex  | age   | stuNum    | classNum
    '张三丰'    |   1    | 23    | "201424430231" | "2014244302"
  }

  def "edit-test" () {
    given:
    def stu = new StuMessage()
    stu.setStuName("dongxiaohua")
    stu.setId(9)
    stu.setStuSex(1)
    stu.setStuAge(24)
    stu.setStuNumber("201424430203")
    stu.setClassNum("2014244302")
    expect:
    println "==========" + stuMessageMapper.edit(stu)
  }

}
