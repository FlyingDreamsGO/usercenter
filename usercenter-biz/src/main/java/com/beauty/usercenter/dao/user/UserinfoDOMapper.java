package com.beauty.usercenter.dao.user;

import com.beauty.usercenter.client.domain.UserinfoDO;

public interface UserinfoDOMapper {
    int deleteByPrimaryKey(long id);

    int insert(UserinfoDO record);

    int insertSelective(UserinfoDO record);

    UserinfoDO selectByPrimaryKey(long id);

    int updateByPrimaryKeySelective(UserinfoDO record);

    int updateByPrimaryKey(UserinfoDO record);
}