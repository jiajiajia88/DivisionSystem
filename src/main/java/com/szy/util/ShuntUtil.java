package com.szy.util;

import com.alibaba.fastjson.JSON;
import com.szy.db.mapper.PlanMapper;
import com.szy.db.mapper.StuInfoMapper;
import com.szy.db.mapper.VolunteerMapper;
import com.szy.db.model.*;
import com.szy.vo.NewClassKey;
import com.szy.vo.PlanKey;
import com.szy.vo.PlanUnit;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created by shizhouyong on 2017/3/15.
 */

public class ShuntUtil {

    public static Map<PlanKey, ShuntUtil> shuntUtilMap = new ConcurrentHashMap<>();

    private List<PlanUnit> planUnits;
    private List<StudentInfoQueryDbo> studentInfoQueryDbos;
    private List<VolunteerQueryDbo> volunteerQueryDbos;
    private Map<Integer, List<VolunteerQueryDbo>> targetMap;
    private Map<Integer, PlanUnit> planUnitMap;

    private int category;
    private int grade;

    public ShuntUtil(int category, int grade) {
        this.category = category;
        this.grade = grade;
    }

    public void executeShunt() {
        initPlanUnit();
        initStudentInfoQueryDbos();
        initVolunteerQueryDbos();
        initTargetMap();
        initPlanUnitMap();

        List<VolunteerQueryDbo> pendList = new LinkedList<>();

        //第一个循环
        pendList.addAll(this.volunteerQueryDbos);
        for (VolunteerQueryDbo dbo : pendList) {
            int firstChoose = dbo.getFirstChooseId();
            if (targetMap.containsKey(firstChoose)){
                targetMap.get(firstChoose).add(dbo);
            }
            this.volunteerQueryDbos.remove(dbo);
        }
        dealTargetMap();


        //第二个循环
        pendList.clear();
        pendList.addAll(this.volunteerQueryDbos);
        for (VolunteerQueryDbo dbo : pendList) {
            int secondChoose = dbo.getSecondChooseId();
            if (targetMap.containsKey(secondChoose)){
                if (targetMap.get(secondChoose).size() < this.planUnitMap.get(secondChoose).getStuAmount()) {
                    targetMap.get(secondChoose).add(dbo);
                    this.volunteerQueryDbos.remove(dbo);
                }
            }
        }
        dealTargetMap();

        //第三个循环
        pendList.clear();
        pendList.addAll(this.volunteerQueryDbos);
        for (VolunteerQueryDbo dbo : pendList) {
            int thirdChoose = dbo.getThirdChooseId();
            if (targetMap.containsKey(thirdChoose)){
                if (targetMap.get(thirdChoose).size() < this.planUnitMap.get(thirdChoose).getStuAmount()) {
                    targetMap.get(thirdChoose).add(dbo);
                    this.volunteerQueryDbos.remove(dbo);
                }
            }
        }
        dealTargetMap();

        //最后一个循环
        pendList.clear();
        pendList.addAll(this.volunteerQueryDbos);
        for (VolunteerQueryDbo dbo : pendList) {
            for (Map.Entry<Integer, List<VolunteerQueryDbo>> entry : targetMap.entrySet()) {
                if (entry.getValue().size() < planUnitMap.get(entry.getKey()).getStuAmount()) {
                    entry.getValue().add(dbo);
                    break;
                }
            }
        }

    }

    private void dealTargetMap() {
        this.targetMap.forEach((k,v)->{
            int num = this.planUnitMap.get(k).getStuAmount();
            if (v.size() > num) {
                v = v.stream().sorted(Comparator.comparing(VolunteerQueryDbo::getShuntStatus).thenComparing(VolunteerQueryDbo::getRank)).collect(Collectors.toList());
                List<VolunteerQueryDbo> list2 = v.subList(num, v.size());
                v = v.subList(0, num);
                this.targetMap.put(k, v);
                this.volunteerQueryDbos.addAll(list2);
            }
            v.forEach(t->t.setShuntStatus(1));
        });
    }

    public Map<NewClassKey, List<VolunteerQueryDbo>> getNewClassMap(int majorId) {
        Map<NewClassKey, List<VolunteerQueryDbo>> map = new ConcurrentHashMap<>();
        List<VolunteerQueryDbo> v = this.targetMap.get(majorId);

        PlanUnit planUnit = this.planUnitMap.get(majorId);
        int num = planUnit.getClassAmount();
        int eachNum = v.size() / num + 1;
        v = v.stream().sorted(Comparator.comparing(VolunteerQueryDbo::getNumber).reversed()).collect(Collectors.toList());
        for(int i = 0;i < num - 1;i++){
            map.put(new NewClassKey(majorId,i+1),v.subList(i * eachNum, (i+1) * eachNum));
        }
        map.put(new NewClassKey(majorId,num), v.subList((num-1)*eachNum, v.size()));
        return map;
    }

    private Map<Integer, PlanUnit> initPlanUnitMap() {
        planUnitMap = this.planUnits.stream().collect(Collectors.toMap(PlanUnit::getId, (p) -> p));
        return this.planUnitMap;
    }

    private Map<Integer, List<VolunteerQueryDbo>> initTargetMap() {
        Map<Integer, List<VolunteerQueryDbo>> map = new ConcurrentHashMap<>();
        this.planUnits.forEach(t -> map.put(t.getId(), new ArrayList<>()));
        this.targetMap = map;
        return this.targetMap;
    }

    private List<PlanUnit> initPlanUnit() {
        if (this.planUnits == null) {
            GetPlanItems items = new GetPlanItems();
            items.setCategory(this.category);
            items.setGrade(this.grade);
            items.setStatus(2);

            PlanMapper planMapper = DBUtil.getMapper(PlanMapper.class);
            List<PlanQueryDbo> planDbos = planMapper.selectPlans(items);
            if (planDbos == null || planDbos.isEmpty() || planDbos.size() != 1)
                return null;

            planUnits = new ArrayList<>();
            for (PlanQueryDbo planDbo : planDbos) {
                List<PlanUnit> planUnitList = JSON.parseArray(planDbo.getDetails(), PlanUnit.class);
                if (planUnitList == null || planUnitList.isEmpty())
                    return null;
                planUnits.addAll(planUnitList);
            }
            this.planUnits = planUnits;
        }
        return this.planUnits;
    }

    private List<StudentInfoQueryDbo> initStudentInfoQueryDbos() {
        if (this.studentInfoQueryDbos == null) {
            GetStuInfoItems items = new GetStuInfoItems();
            items.setFrom(0);
            items.setSize(1000 * 1000 * 1000);
            items.setCategory(this.category);
            items.setGrade(this.grade);

            StuInfoMapper mapper = DBUtil.getMapper(StuInfoMapper.class);
            studentInfoQueryDbos = mapper.selectStudentInfoList(items);
        }
        return this.studentInfoQueryDbos;
    }

    private List<VolunteerQueryDbo> initVolunteerQueryDbos() {
        if (this.volunteerQueryDbos == null) {
            GetVolunteerItems items = new GetVolunteerItems();
            items.setFrom(0);
            items.setSize(1000 * 1000 * 1000);
            items.setCategory(this.category);
            items.setGrade(this.grade);

            VolunteerMapper mapper = DBUtil.getMapper(VolunteerMapper.class);
            volunteerQueryDbos = mapper.selectVolunteerList(items);
        }

        return this.volunteerQueryDbos;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public List<PlanUnit> getPlanUnits() {
        return planUnits;
    }

    public List<StudentInfoQueryDbo> getStudentInfoQueryDbos() {
        return studentInfoQueryDbos;
    }

    public List<VolunteerQueryDbo> getVolunteerQueryDbos() {
        return volunteerQueryDbos;
    }

    public Map<Integer, List<VolunteerQueryDbo>> getTargetMap() {
        return targetMap;
    }

    public Map<Integer, PlanUnit> getPlanUnitMap() {
        return planUnitMap;
    }

}
