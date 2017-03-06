package com.szy.db.mapper;

import com.szy.db.model.Items;
import com.szy.db.model.UserDbo;

import java.util.List;

/**
 * Created by shizhouyong on 2017/1/7.
 */
public interface UserMapper {

    public int insertUser(UserDbo userDbo);
    public UserDbo selectUserByNumber(long number);
    public List<UserDbo> selectUsers(Items items);
    public int updateUser(UserDbo userDbo);
    public int updatePassword(UserDbo userDbo);
}
