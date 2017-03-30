package com.beauty.usercenter.manager;

import com.beauty.usercenter.client.domain.UserinfoDO;
import com.beauty.usercenter.dao.user.UserinfoDOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by kenan@xiaokakeji.com
 * on 2017/3/20.
 */
@Repository
public class UserInfoManager {

    @Autowired
    private UserinfoDOMapper userinfoDOMapper;

    public void createUserInfo(UserinfoDO userinfoDO){
        userinfoDOMapper.insert(userinfoDO);
    }



}
