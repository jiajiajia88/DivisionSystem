package com.szy.db.mapper;

import com.szy.db.model.ChooseDbo;
import com.szy.db.model.GetStuInfoItems;
import com.szy.db.model.StudentInfoDbo;
import com.szy.db.model.VolunteerDbo;

import java.util.List;

/**
 * 志愿信息和学生信息
 * Created by shizhouyong on 2017/1/25.
 */
public interface StuInfoMapper {

    public int insertStudentInfo(StudentInfoDbo dbo);

    public StudentInfoDbo selectStudentInfoByNumber(long number);

    public List<StudentInfoDbo> selectStudentInfoList(GetStuInfoItems items);

    public int insertChoose(ChooseDbo dbo);

}
