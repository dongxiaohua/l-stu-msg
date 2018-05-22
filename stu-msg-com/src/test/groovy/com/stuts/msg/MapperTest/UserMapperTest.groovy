package com.stuts.msg.MapperTest

import com.stus.msg.entity.StuUser
import com.stus.msg.mapper.StuUserMapper
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
class UserMapperTest extends Specification {
  @Autowired
  StuUserMapper userMapper

  def "userMapper-findUser"() {
    given:
    def name = "admin"
    def pwd = "123456"
    expect:
    println userMapper.find(name, pwd)

  }

  def "findAll"() {
    expect:
    println "=========" + userMapper.findAll()
  }

  def "insert"() {
    given:
    def stuUser = new StuUser()
    stuUser.userName = "201424430203"
    stuUser.passWord = "201424430203"
    stuUser.rights = "user"
    expect:
    println "==============" + userMapper.insert(stuUser)
  }

}
