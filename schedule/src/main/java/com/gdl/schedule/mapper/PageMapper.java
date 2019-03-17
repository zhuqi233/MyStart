package com.gdl.schedule.mapper;

import com.gdl.schedule.entity.Item;
import com.gdl.schedule.entity.Plan;
import org.apache.ibatis.annotations.Param;
import java.util.Date;
import java.util.List;

public interface PageMapper {

	/**
	 * 根据用户id查询计划
	 * @param
	 * @return startPos},#{pageSize}
	 */
	public List<Plan> selectPlansByUserID(@Param(value = "startPos") Integer startPos,
                                          @Param(value = "pageSize") Integer pageSize,
                                          @Param(value = "userID") Integer userID);

	/**
	 * 查询所有计划
	 * @param startPos
	 * @param pageSize
	 * @return
	 */
	public List<Plan> selectAllPlans(@Param(value = "startPos") Integer startPos,
                                    @Param(value = "pageSize") Integer pageSize);

	/**
	 * 根据项目id查询计划
	 * @param startPos
	 * @param pageSize
	 * @param itemID
	 * @return
	 */
	public List<Plan> findPlansByItemID(@Param(value = "startPos") Integer startPos,
										@Param(value = "pageSize") Integer pageSize,
										@Param(value = "itemID") Integer itemID);

	/**
	 * 根据关键字查询计划
	 * @param planWork
	 * @return Plan集合
	 */
	public List<Plan> findPlansByKeyword(@Param(value = "startPos") Integer startPos,
										 @Param(value = "pageSize") Integer pageSize,
										 @Param(value = "itemID") Integer itemID,
										 @Param(value = "planWork") String planWork);

//	public List<Plan> findAllBookByDCAndPage(@Param(value = "code") Integer code,
//                                             @Param(value = "startPos") Integer startPos,
//                                             @Param(value = "pageSize") Integer pageSize,
//                                             @Param(value = "starttime") Date starttime,
//                                             @Param(value = "endtime") Date endtime);
//

	/**
	 * 计划总数
	 */
	public Integer getPlanCountByUid(@Param(value = "userID") Integer userID);

	public Integer getPlanCountByItemID(@Param(value = "itemID") Integer itemID);

	public Integer getPlanCountByKeyword(@Param(value = "planWork") String planWork);
	
	public Integer getAllPlanCount();
	
//	public long getPlanCountByDate(@Param(value = "starttime") Date starttime,
//                                   @Param(value = "endtime") Date endtime);
//
//	public long getPlanCountByDateAndCode(@Param(value = "code") Integer code,
//                                          @Param(value = "starttime") Date starttime,
//                                          @Param(value = "endtime") Date endtime);
//

}
