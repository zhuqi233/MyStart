package com.gdl.schedule.service;

import com.gdl.schedule.entity.Plan;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface PlanService {
    /**
     * 查询所有计划
     * @param
     * @return Plan对象集合
     */
    public List<Plan> findAllPlans();

    /**
     * 根据id查询计划
     * @param id
     * @return Plan对象
     */
    public Plan findPlanById(Integer id);

    /**
     * 根据项目ID查询计划
     * @param request
     * @return  Plan Page Item集合
     */
    public Map<String,Object> findPlansByItemID(HttpServletRequest request, Integer itemID);

    /**
     * 根据用户ID查询计划
     * @param request
     * @return  Plan Page Item集合
     */
    public Map<String,Object> findPlansByUid(HttpServletRequest request, Integer userID);

    /**
     * 根据关键字查询计划
     * @param request
     * @return Plan Page Item集合
     */
    public Map<String,Object> findPlansByKeyword(HttpServletRequest request);

    /**
     * 新建计划
     * @param
     * @return 新增项目id
     */
    public Integer insertItem(Plan plan);

    /**
     * 修改计划
     * @param
     * @return  修改的数据数量
     */
    public Integer updatePlan(Plan plan);

    /**
     * 根据id删除项目
     * @param id
     * @return  删除的数据数量
     */
    public Integer deletePlan(Integer id);

    /**
     * 批量删除
     * @param planIds id集合
     * @return
     */
    public Integer deleteMorePlans(List<Integer> planIds);
}
