package com.stus.msg.web;

import com.stus.msg.entity.StuUser;
import com.stus.msg.service.StuClassService;
import com.stus.msg.service.StuUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author dongxiaohua
 * @date 2018/5/13
 */
@RestController
@Slf4j
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private StuUserService stuUserService;

    /**
     * 获取所有用户
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<StuUser> list() {
        return stuUserService.findAllUser();
    }

}
