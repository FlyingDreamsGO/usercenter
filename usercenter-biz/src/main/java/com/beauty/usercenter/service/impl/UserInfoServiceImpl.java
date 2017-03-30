package com.beauty.usercenter.service.impl;

import com.beauty.usercenter.client.domain.UserinfoDO;
import com.beauty.usercenter.client.service.UserInfoService;
import com.beauty.usercenter.manager.UserInfoManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by kenan@xiaokakeji.com
 * on 2017/3/20.
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoManager userInfoManager;

    @Override
    public void createUserInfo(UserinfoDO userinfoDO) {
        userInfoManager.createUserInfo(userinfoDO);
    }
}
