package com.szy.util;

import com.szy.db.mapper.UserMapper;
import com.szy.db.model.StudentInfoDbo;
import com.szy.db.model.UserDbo;
import org.springframework.stereotype.Component;

/**
 *
 * Created by shizhouyong on 2017/3/13.
 */

@Component
public class AccountCreateUtil {

    public boolean createStuAccount(StudentInfoDbo studentInfoDbo) {

        if (studentInfoDbo == null) {
            return false;
        }

        UserMapper userMapper = DBUtil.getMapper(UserMapper.class);
        UserDbo userDbo = new UserDbo();
        userDbo.setNumber(studentInfoDbo.getNumber());
        userDbo.setName(studentInfoDbo.getName());
        userDbo.setPassword("123456");
        userDbo.setLimit(-1);
        userDbo.setCreateTime(studentInfoDbo.getCreateTime());
        return 1 == userMapper.insertUser(userDbo);
    }
}
