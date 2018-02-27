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
    'a'    |   1    | 23    | "1231231" | "2014244302"
    'f'    |   1    | 23    | "1246233" | "2014244302"
    's'    |   1    | 23    | "6345345" | "2014244302"
    'd'    |   1    | 23    | "3673453" | "2014244302"
    'a'    |   1    | 23    | "6742342" | "2014244302"
    'j'    |   1    | 23    | "4565466" | "2014244302"
    'k'    |   1    | 23    | "3423444" | "2014244302"
    'd'    |   1    | 23    | "1231231" | "2014244302"
    'g'    |   1    | 23    | "4523423" | "2014244302"
    'l'    |   1    | 23    | "7834553" | "2014244302"
    'a'    |   1    | 23    | "1231231" | "2014244302"
    'f'    |   1    | 23    | "1246233" | "2014244302"
    's'    |   1    | 23    | "6345345" | "2014244302"
    'd'    |   1    | 23    | "3673453" | "2014244302"
    'a'    |   1    | 23    | "6742342" | "2014244302"
    'j'    |   1    | 23    | "4565466" | "2014244302"
    'k'    |   1    | 23    | "3423444" | "2014244302"
    'd'    |   1    | 23    | "1231231" | "2014244302"
    'g'    |   1    | 23    | "4523423" | "2014244302"
    'l'    |   1    | 23    | "7834553" | "2014244302"
    'd'    |   1    | 23    | "3673453" | "2014244302"
    'a'    |   1    | 23    | "6742342" | "2014244302"
    'j'    |   1    | 23    | "4565466" | "2014244302"
    'k'    |   1    | 23    | "3423444" | "2014244302"
    'd'    |   1    | 23    | "1231231" | "2014244302"
    'g'    |   1    | 23    | "4523423" | "2014244302"
    'l'    |   1    | 23    | "7834553" | "2014244302"
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
