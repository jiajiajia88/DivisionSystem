package com.szy.util;

import com.szy.RespEnum;
import com.szy.Response;
import com.szy.db.mapper.SystemMapper;
import com.szy.db.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 系统信息工具类，通过内存存取基本信息，提高访问速度
 * Created by shizhouyong on 2017/1/9.
 */
@Component
public class SystemInfoUtil {

    private final static String GRADE = "GRADE";
    private final static String POSITION = "POSITION";
    private final static String MAJOR = "MAJOR";
    private final static String CATEGORY = "CATEGORY";

    private static Long uptime_g = 0L;
    private static Long uptime_p = 0L;
    private static Long uptime_m = 0L;
    private static Long uptime_c = 0L;
    private final static long CACHE_TIME = 24 * 60 * 60 * 1000;

    private static volatile Map<String, SystemInfo> gradeMap = new ConcurrentHashMap<>();
    private static volatile Map<String, SystemInfo> positionMap = new ConcurrentHashMap<>();
    private static volatile Map<String, SystemInfo> majorMap = new ConcurrentHashMap<>();
    private static volatile Map<String, SystemInfo> categoryMap = new ConcurrentHashMap<>();

    public SystemInfo getCategoryByName(String name) {
        return categoryMap.get(name);
    }

    public Response addSystemInfo(SystemInfo systemInfo, String type) throws Exception {
        checkUpdate(type);
        SystemMapper systemMapper = DBUtil.getMapper(SystemMapper.class);
        if (type.equals(GRADE) && systemInfo instanceof GradeDbo) {
            if (gradeMap.containsKey(systemInfo.getName()))
                return RespEnum.NAME_DUPLICATE.getResponse();

            try {
                systemMapper.insertGrade(systemInfo);
            } catch (Exception e) {
                e.printStackTrace();
            }
            List<GradeDbo> gradeDbos = systemMapper.selectGrades();
            for (GradeDbo gradeDbo : gradeDbos) {
                gradeMap.put(gradeDbo.getName(), gradeDbo);
            }
        }

        if (type.equals(MAJOR) && systemInfo instanceof MajorDbo) {
            if (majorMap.containsKey(systemInfo.getName()))
                return RespEnum.NAME_DUPLICATE.getResponse();

            systemMapper.insertMajor(systemInfo);
            List<MajorQueryDbo> majorDbos = systemMapper.selectMajors();
            for (MajorQueryDbo majorDbo : majorDbos) {
                majorMap.put(majorDbo.getName(), majorDbo);
            }
        }

        if (type.equals(CATEGORY) && systemInfo instanceof CategoryDbo) {
            if (categoryMap.containsKey(systemInfo.getName()))
                return RespEnum.NAME_DUPLICATE.getResponse();

            systemMapper.insertCategory(systemInfo);
            List<CategoryDbo> categoryDbos = systemMapper.selectCategories();
            for (CategoryDbo categoryDbo : categoryDbos) {
                categoryMap.put(categoryDbo.getName(), categoryDbo);
            }
        }

        if (type.equals(POSITION) && systemInfo instanceof PositionDbo) {
            if (positionMap.containsKey(systemInfo.getName()))
                return RespEnum.NAME_DUPLICATE.getResponse();

            systemMapper.insertPosition(systemInfo);
            List<PositionDbo> positionDbos = systemMapper.selectPositions();
            for (PositionDbo positionDbo : positionDbos) {
                positionMap.put(positionDbo.getName(), positionDbo);
            }
        }


        return RespEnum.SUCCESS.getResponse();
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

        SystemMapper systemMapper = DBUtil.getMapper(SystemMapper.class);
        switch (type) {
            case GRADE:
                synchronized (uptime_g) {
                    if (cur - uptime_g < CACHE_TIME)
                        return;

                    List<GradeDbo> gradeDbos = systemMapper.selectGrades();
                    for (GradeDbo gradeDbo : gradeDbos) {
                        gradeMap.put(gradeDbo.getName(), gradeDbo);
                    }
                    uptime_g = cur;
                }
                return;
            case MAJOR:
                synchronized (uptime_m) {
                    if (cur - uptime_m < CACHE_TIME)
                        return;

                    List<MajorQueryDbo> majorDbos = systemMapper.selectMajors();
                    for (MajorQueryDbo majorDbo : majorDbos) {
                        majorMap.put(majorDbo.getName(), majorDbo);
                    }
                    uptime_m = cur;
                }
                return;
            case CATEGORY:
                synchronized (uptime_c) {
                    if (cur - uptime_c < CACHE_TIME)
                        return;

                    List<CategoryDbo> categoryDbos = systemMapper.selectCategories();
                    for (CategoryDbo categoryDbo : categoryDbos) {
                        categoryMap.put(categoryDbo.getName(), categoryDbo);
                    }
                    uptime_c = cur;
                }
                return;
            case POSITION:
                synchronized (uptime_p) {
                    if (cur - uptime_p < CACHE_TIME)
                        return;
                    List<PositionDbo> positionDbos = systemMapper.selectPositions();
                    for (PositionDbo positionDbo : positionDbos) {
                        positionMap.put(positionDbo.getName(), positionDbo);
                    }
                    uptime_p = cur;
                }
                return;
            default:
        }

    }

}
