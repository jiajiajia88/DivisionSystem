package com.szy.db.mapper;

import com.szy.db.model.*;

import java.util.List;

/**
 * 志愿信息和学生信息
 * Created by shizhouyong on 2017/1/25.
 */
public interface StuInfoMapper {

    public int insertStudentInfo(StudentInfoDbo dbo);

    public StudentInfoQueryDbo selectStudentInfoByNumber(long number);

    public List<StudentInfoQueryDbo> selectStudentInfoList(GetStuInfoItems items);

    public int selectStudentInfoListTotal(GetStuInfoItems items);

    public int insertChoose(ChooseDbo dbo);

    public int updatePhone(UpdatePhoneDbo dbo);

    public int updateGrade(StudentInfoDbo dbo);

    public int updateRank(StudentInfoDbo dbo);

    public int updateGradeAndCategory(StudentInfoDbo dbo);

    public int updateNewClass(StudentInfoDbo dbo);

    public int updateNewMajor(StudentInfoDbo dbo);

}
