package com.szy.db.mapper;

import com.szy.db.model.*;

import java.util.List;

/**
 * Created by shizhouyong on 2017/3/15.
 */
public interface ShuntResultMapper {

    public int insertNewMajor(NewMajorDbo dbo);

    public int updateNewMajor(NewMajorDbo dbo);

    public List<NewMajorQueryDbo> getNewMajorList(GetNewMajorItems items);

    public int getNewMajorListTotal(GetNewMajorItems items);

    public int deleteNewMajor(int id);

    public int insertNewClass(NewClassDbo dbo);

    public int updateNewClass(NewClassDbo dbo);

    public List<NewClassQueryDbo> getNewClassList(GetNewClassItems items);

    public int getNewClassListTotal(GetNewClassItems items);

    public int deleteNewClass(int id);

}
