package com.szy.db.mapper;

import com.szy.db.model.GetStuInfoItems;
import com.szy.db.model.StudentInfoDbo;
import com.szy.db.model.VolunteerDbo;

import java.util.List;

/**
 * Created by shizhouyong on 2017/1/25.
 */
public interface VolunteerMapper {

    public int insertVolunteer(VolunteerDbo dbo);

    public VolunteerDbo getVolunteerByNumber(long number);

    public List<VolunteerDbo> getVolunteerList();

    public int deleteVolunteer(long number);

    public int insertStudentInfo(StudentInfoDbo dbo);

    public StudentInfoDbo selectStudentInfoByNumber(long number);

    public List<StudentInfoDbo> selectStudentInfoList(GetStuInfoItems items);

}
