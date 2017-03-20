package com.szy.db.mapper;

import com.szy.db.model.*;

import java.util.List;

/**
 * Created by shizhouyong on 2017/3/15.
 */
public interface ShuntMapper {

    public int insertNewMajor(NewMajorDbo dbo);

    public int updateNewMajor(NewMajorDbo dbo);

    public List<NewMajorQueryDbo> selectNewMajorList(GetNewMajorItems items);

    public int selectNewMajorListTotal(GetNewMajorItems items);

    public NewMajorQueryDbo selectMajorById(int id);

    public int deleteNewMajor(int id);

    public int insertNewClass(NewClassDbo dbo);

    public int updateNewClass(NewClassDbo dbo);

    public List<NewClassQueryDbo> selectNewClassList(GetNewClassItems items);

    public int selectNewClassListTotal(GetNewClassItems items);

    public int deleteNewClass(int id);

    public List<NewMajorQueryDbo> selectNewMajorAsOptionItems(GetNewMajorItems items);

    public List<NewClassQueryDbo> selectNewClassAsOptionItems(GetNewClassItems items);

}
