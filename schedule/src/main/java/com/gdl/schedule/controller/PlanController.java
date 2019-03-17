package com.gdl.schedule.controller;

import com.gdl.schedule.entity.Plan;
import com.gdl.schedule.entity.ResponseResult;
import com.gdl.schedule.service.ItemService;
import com.gdl.schedule.service.PlanService;
import com.gdl.schedule.util.GeneralUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class PlanController {

    @Resource(name = "planService")
    private PlanService planService;
    @Resource(name = "itemService")
    private ItemService itemService;

    @RequestMapping("/select_all_plans")
    @ResponseBody
    public ResponseResult select_all_plans(HttpServletRequest request, HttpSession session){
        System.err.println("----------查询开始----------");
        ResponseResult rr;
        Integer userID = (Integer) session.getAttribute("uid");
        Map map = planService.findPlansByUid(request,userID);
        if (GeneralUtil.isEmpty(map)){
            rr = new ResponseResult(0,"查询失败");
        }else{
            rr = new ResponseResult(1,map);
        }
        System.err.println("----------查询结束----------");
        return rr;
    }

    @RequestMapping("/selectPlanByItem")
    @ResponseBody
    public ResponseResult selectPlanByItem(HttpServletRequest request){
        ResponseResult rr;
        String str = request.getParameter("itemID");
        if (GeneralUtil.isEmpty(str)){
            rr = new ResponseResult(-1,"项目名为空");
            return rr;
        }
        Integer itemID = Integer.parseInt(str);
//        Integer itemID = itemService.findItemIdByItemname(itemname);
        Map map = planService.findPlansByItemID(request,itemID);
        if (GeneralUtil.isEmpty(map)){
            rr = new ResponseResult(0,"查询失败");
        }else{
            rr = new ResponseResult(1,map);
        }
        return rr;
    }

    @RequestMapping("/selectPlanByKeyword")
    @ResponseBody
    public ResponseResult selectPlanByKeyword(HttpServletRequest request){
        ResponseResult rr;
        String planWork = request.getParameter("planWork");
        if (GeneralUtil.isEmpty(planWork)){
            rr = new ResponseResult(-1);
            return rr;
        }
        Map map = planService.findPlansByKeyword(request);
        if (GeneralUtil.isEmpty(map)){
            rr = new ResponseResult(0,"查询失败");
        }else{
            rr = new ResponseResult(1,map);
        }
        return rr;
    }

    @RequestMapping("/selectPlanByPlanId")
    @ResponseBody
    public ResponseResult selectPlanByPid(String planId){
        ResponseResult rr;
        if (GeneralUtil.isEmpty(planId)){
            rr = new ResponseResult(-1,"id为空");
            return rr;
        }
        Plan plan = planService.findPlanById(Integer.parseInt(planId));
        if (plan==null){
            rr = new ResponseResult(0,"查询失败");
        }else {
            rr = new ResponseResult(1,plan);
        }
        return rr;
    }

    /**
     * 添加或修改计划
     * @param planDate  计划时间
     * @param planWork  计划安排
     * @param actualWork  实际进度
     * @param actualWorkDate  进度记录时间
     * @param completeDate  实际完成时间
     * @param complete  是否完成
     * @param itemID    项目ID
     * @param id    计划ID
     * @param session
     * @return
     */
    @RequestMapping(value = "/add_plan",method= RequestMethod.POST)
    public String add_plan(String planDate,String planWork,String actualWork,String actualWorkDate,
                           String completeDate,String complete,String itemID,String id,HttpSession session){
        System.err.println("----------新增/修改开始----------");
        System.err.println("itemID:"+itemID);
        Integer userID = (Integer) session.getAttribute("uid");
        Integer completes = Integer.parseInt(complete);
        Plan plan;
        if (GeneralUtil.isEmpty(id)){
            plan = new Plan();
        }else {
            plan = planService.findPlanById(Integer.parseInt(id));
        }
        plan.setPlanDate(GeneralUtil.formatDate(planDate));
        plan.setPlanWork(planWork);
        if (!GeneralUtil.isEmpty(actualWork))
            plan.setActualWork(actualWork);
        if (!GeneralUtil.isEmpty(actualWorkDate))
            plan.setActualWorkDate(GeneralUtil.formatDate(actualWorkDate));
        plan.setComplete(completes);
        if (!GeneralUtil.isEmpty(completeDate))
            plan.setCompleteDate(GeneralUtil.formatDate(completeDate));
        if (!GeneralUtil.isEmpty(itemID))
            plan.setItemID(Integer.parseInt(itemID));
        plan.setUserID(userID);
        if (GeneralUtil.isEmpty(id)){
            Integer idd = planService.insertItem(plan);
            System.err.println("新增成功，id:"+idd);
        }else {
            Integer idd =  planService.updatePlan(plan);
            System.err.println("修改成功，index:"+idd);
        }
        System.err.println(plan);
        System.err.println("----------新增/修改结束----------");
        return "add_plan";
    }

    @RequestMapping(value = "/delete_plan",method= RequestMethod.POST)
    @ResponseBody
    public ResponseResult delete_plan(@RequestParam("id") String id){
        System.err.println("----------删除开始----------");
        ResponseResult rr;
        Integer index = planService.deletePlan(Integer.parseInt(id));
        if(index>0){
            rr = new ResponseResult(1,"删除成功");
        } else {
            rr = new ResponseResult(0,"删除失败");
        }
        System.out.println(rr);
        System.err.println("----------删除结束----------");
        return rr;
    }

    @RequestMapping(value="/handleMoreDelete",method=RequestMethod.POST)
    @ResponseBody
    public ResponseResult<Void> handleMoreDelete(String checkStr){
        ResponseResult<Void> rr;
        List<Integer> planIds = new ArrayList<Integer>();
        String[] ps = checkStr.split(",");
        for(String b : ps){
            planIds.add(Integer.parseInt(b));
        }
        Integer index = planService.deleteMorePlans(planIds);
        System.out.println("index:"+index);
        if (index>0){
            rr = new ResponseResult<Void>(1,"删除成功");
        }else {
            rr = new ResponseResult<Void>(0,"删除失败");
        }
        return rr;
    }
}
