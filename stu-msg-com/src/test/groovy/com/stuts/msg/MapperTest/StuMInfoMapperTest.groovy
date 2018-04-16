package com.stuts.msg.MapperTest

import com.stus.msg.entity.StuInfo
import com.stus.msg.mapper.StuInfoMapper
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
class StuMInfoMapperTest extends Specification {
  @Autowired
  StuInfoMapper stuInfoMapper

  def "insert-test"() {
    given:
    def info = new StuInfo()
    info.setStuNumber("201424430203")
    info.setInfoName("荣誉")
    info.setStuContent("三好生")
    info.setStuReason("年级第三")
    info.setTheTime(new Date())
    info.setEndTime(new Date())
    info.setStuOff(0)
    expect:
    println "==========" + stuInfoMapper.insert(info)
  }

  def "edit"() {
    given:
    def info = new StuInfo()
    info.setStuNumber("201424430203")
    info.setInfoName("荣誉")
    info.setStuContent("三好xue生")
    info.setStuReason("年级第三")
    info.setTheTime(new Date())
    info.setEndTime(new Date())
    info.setStuOff(0)
    info.setId(3)
    expect:
    println "===========" + stuInfoMapper.edit(info)
  }

}
