package com.beauty.usercenter.controller;

import com.beauty.usercenter.client.domain.UserinfoDO;
import com.beauty.usercenter.client.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kenan@xiaokakeji.com
 * on 2017/3/19.
 */

@Controller
@RequestMapping("/test")
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Resource
    private UserInfoService userInfoService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    @ResponseBody
    public void create() {
        UserinfoDO userinfoDO = new UserinfoDO();
        userinfoDO.setAge(18);
        userinfoDO.setName("我爱罗哈哈哈");
        userinfoDO.setBirthday("火影忍者吼吼吼");
        userInfoService.createUserInfo(userinfoDO);
    }


    @RequestMapping(value = "/login/", method = RequestMethod.POST)
    public void login() {
        System.out.println("登录");
        Map map = new HashMap();


    }
}
