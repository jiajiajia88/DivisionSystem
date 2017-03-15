package com.szy.db.mapper;

import com.szy.db.model.*;

import java.util.List;

/**
 * Created by shizhouyong on 2017/1/7.
 */
public interface UserMapper {

    public int insertUser(UserDbo userDbo);

    public UserDbo selectUserByNumber(long number);

    public List<StuAccountDbo> selectStuAccount(GetStuAccountItems items);

    public int selectStuAccountTotal(GetStuAccountItems items);

    public int updateUser(UserDbo userDbo);

    public int updatePassword(UserDbo userDbo);

    public int deleteUser(long number);

    public int updateUserLimit(UpdateUserLimitDbo dbo);

}
