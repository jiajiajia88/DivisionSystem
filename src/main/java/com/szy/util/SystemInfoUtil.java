package com.szy.util;

import com.szy.RespEnum;
import com.szy.Response;
import com.szy.db.mapper.SystemMapper;
import com.szy.db.model.CategoryDbo;
import com.szy.db.model.GradeDbo;
import com.szy.db.model.MajorDbo;
import com.szy.db.model.PositionDbo;
import com.szy.model.SystemInfo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shizhouyong on 2017/1/9.
 */
@Component
public class SystemInfoUtil {

    private final static String GRADE = "GRADE";
    private final static String POSITION = "POSITION";
    private final static String MAJOR = "MAJOR";
    private final static String CATEGORY = "CATEGORY";

    private static Long uptime = 0L;
    private final static long CACHE_TIME = 24 * 60 * 60 * 1000;

    private static volatile Map<String, SystemInfo> gradeMap = new HashMap<>();
    private static volatile Map<String, SystemInfo> positionMap = new HashMap<>();
    private static volatile Map<String, SystemInfo> majorMap = new HashMap<>();
    private static volatile Map<String, SystemInfo> categoryMap = new HashMap<>();

    public SystemInfo addSystemInfo(SystemInfo systemInfo, String type) throws Exception {
        checkUpdate(type);
        SystemMapper systemMapper = DBUtil.getMapper(SystemMapper.class);
        if (type.equals(GRADE) && systemInfo instanceof GradeDbo) {
            if (gradeMap.containsKey(systemInfo.getName()))
                return gradeMap.get(systemInfo.getName());

            systemMapper.insertGrade(systemInfo);
            List<GradeDbo> gradeDbos = systemMapper.selectGrades();
            for (GradeDbo gradeDbo : gradeDbos) {
                gradeMap.put(gradeDbo.getName(), gradeDbo);
            }
            return systemInfo;
        }

        if (type.equals(MAJOR) && systemInfo instanceof MajorDbo) {
            if (majorMap.containsKey(systemInfo.getName()))
                return majorMap.get(systemInfo.getName());

            systemMapper.insertMajor(systemInfo);
            List<MajorDbo> majorDbos = systemMapper.selectMajors();
            for (MajorDbo majorDbo : majorDbos) {
                majorMap.put(majorDbo.getName(), majorDbo);
            }
            return systemInfo;
        }

        if (type.equals(CATEGORY) && systemInfo instanceof CategoryDbo) {
            if (categoryMap.containsKey(systemInfo.getName()))
                return categoryMap.get(systemInfo.getName());

            systemMapper.insertCategory(systemInfo);
            List<CategoryDbo> categoryDbos = systemMapper.selectCategories();
            for (CategoryDbo categoryDbo : categoryDbos) {
                categoryMap.put(categoryDbo.getName(), categoryDbo);
            }
            return systemInfo;
        }

        if (type.equals(POSITION) && systemInfo instanceof PositionDbo) {
            if (positionMap.containsKey(systemInfo.getName()))
                return positionMap.get(systemInfo.getName());

            systemMapper.insertPosition(systemInfo);
            List<PositionDbo> positionDbos = systemMapper.selectPositions();
            for (PositionDbo positionDbo : positionDbos) {
                positionMap.put(positionDbo.getName(), positionDbo);
            }
            return systemInfo;
        }

        return null;
    }

    public Response deleteSystemInfo(String name, String type) throws Exception {
        checkUpdate(type);
        SystemMapper systemMapper = DBUtil.getMapper(SystemMapper.class);
        switch (type) {
            case GRADE:
                if (!gradeMap.containsKey(name))
                    return RespEnum.DATA_NOT_FOUND.getResponse();


                try {
                    systemMapper.deleteGrade(gradeMap.get(name).getId());
                } catch (Exception e) {
                    e.printStackTrace();
                    return RespEnum.DATA_DELETE_ERR.getResponse();
                }
                gradeMap.remove(name);
                break;
            case MAJOR:
                if (!majorMap.containsKey(name))
                    return RespEnum.DATA_NOT_FOUND.getResponse();
                try {
                    systemMapper.deleteMajor(majorMap.get(name).getId());
                } catch (Exception e) {
                    e.printStackTrace();
                    return RespEnum.DATA_DELETE_ERR.getResponse();
                }
                majorMap.remove(name);
                break;
            case CATEGORY:
                if (!categoryMap.containsKey(name))
                    return RespEnum.DATA_NOT_FOUND.getResponse();
                try {
                    systemMapper.deleteCategory(categoryMap.get(name).getId());
                } catch (Exception e) {
                    e.printStackTrace();
                    return RespEnum.DATA_DELETE_ERR.getResponse();
                }
                categoryMap.remove(name);
                break;
            case POSITION:
                if (!positionMap.containsKey(name))
                    return RespEnum.DATA_NOT_FOUND.getResponse();
                try {
                    systemMapper.deletePosition(positionMap.get(name).getId());
                } catch (Exception e) {
                    e.printStackTrace();
                    return RespEnum.DATA_DELETE_ERR.getResponse();
                }
                positionMap.remove(name);
                break;
            default:
                return RespEnum.TYPE_NOT_FOUND.getResponse();
        }
        return RespEnum.SUCCESS.getResponse();
    }

    public List<SystemInfo> getSystemInfoList(String type) throws Exception {
        checkUpdate(type);
        switch (type) {
            case GRADE:
                return new ArrayList<>(gradeMap.values());
            case MAJOR:
                return new ArrayList<>(majorMap.values());
            case CATEGORY:
                return new ArrayList<>(categoryMap.values());
            case POSITION:
                return new ArrayList<>(positionMap.values());
            default:
                return null;
        }
    }

    private void checkUpdate(String type){

        long cur = System.currentTimeMillis();
        if(cur - uptime < CACHE_TIME)
            return;

        synchronized (uptime) {
            if(cur - uptime < CACHE_TIME)
                return;

            SystemMapper systemMapper = DBUtil.getMapper(SystemMapper.class);

            switch (type) {
                case GRADE:
                    List<GradeDbo> gradeDbos = systemMapper.selectGrades();
                    for (GradeDbo gradeDbo : gradeDbos) {
                        gradeMap.put(gradeDbo.getName(), gradeDbo);
                    }
                    uptime = cur;
                    return;
                case MAJOR:
                    List<MajorDbo> majorDbos = systemMapper.selectMajors();
                    for (MajorDbo majorDbo : majorDbos) {
                        majorMap.put(majorDbo.getName(), majorDbo);
                    }
                    uptime = cur;
                    return;
                case CATEGORY:
                    List<CategoryDbo> categoryDbos = systemMapper.selectCategories();
                    for (CategoryDbo categoryDbo : categoryDbos) {
                        categoryMap.put(categoryDbo.getName(), categoryDbo);
                    }
                    uptime = cur;
                    return;
                case POSITION:
                    List<PositionDbo> positionDbos = systemMapper.selectPositions();
                    for (PositionDbo positionDbo : positionDbos) {
                        positionMap.put(positionDbo.getName(), positionDbo);
                    }
                    uptime = cur;
                    return;
                default:
                    return;
            }
        }
    }

}
