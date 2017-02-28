package com.szy.db.mapper;

import com.szy.db.model.*;

import java.util.List;

/**
 * 志愿信息和学生信息
 * Created by shizhouyong on 2017/1/25.
 */
public interface VolunteerMapper {

    public int insertVolunteer(VolunteerDbo dbo);

    public VolunteerDbo selectVolunteerByNumber(long number);

    public List<VolunteerDbo> selectVolunteerList(GetVolunteerItems items);

    public int selectVolunteerListTotal(GetVolunteerItems items);

    public int deleteVolunteer(long number);

    public int updateVolunteerCommitStatus(VolunteerDbo dbo);

}
