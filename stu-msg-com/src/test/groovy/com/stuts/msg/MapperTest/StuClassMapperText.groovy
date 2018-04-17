package com.stuts.msg.MapperTest

import com.stus.msg.entity.StuClass
import com.stus.msg.mapper.StuClassMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

/**
 * @author dongxiaohua
 * Created on 2018/4/17.
 */
@ContextConfiguration(value = ["classpath:mapperContext.xml"])
class StuClassMapperText extends Specification {
  @Autowired
  StuClassMapper stuClassMapper

  def "add"() {
    given:
    def stuClass = new StuClass()
    stuClass.setClassNum("2014244301")
    stuClass.setClassName("计算机01班")
    stuClass.setClassDepartment("电气信息")
    stuClass.setClassGrade("2014级")
    expect:
    println "=========" + stuClassMapper.insert(stuClass)
  }

}
