package com.gdl.schedule.mapper;

import com.gdl.schedule.entity.Plan;
import java.util.List;

public interface PlanMapper {

    /**
     * 根据id查询计划
     * @param id
     * @return Plan对象
     */
    public Plan findPlanById(Integer id);
    /**
     * 根据项目ID查询计划
     * @param itemID
     * @return  Plan对象集合
     */
    public List<Plan> findPlanByItemID(Integer itemID);

    /**
     * 新建计划
     * @param plan
     * @return 新增计划id
     */
    public Integer insertPlan(Plan plan);

    /**
     * 修改计划
     * @param plan
     * @return  修改的数据数量
     */
    public Integer updatePlan(Plan plan);

    /**
     * 根据id删除计划
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
