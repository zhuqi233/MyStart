package com.gdl.schedule.service.impl;

import com.gdl.schedule.entity.Item;
import com.gdl.schedule.entity.Page;
import com.gdl.schedule.entity.Plan;
import com.gdl.schedule.mapper.ItemMapper;
import com.gdl.schedule.mapper.PageMapper;
import com.gdl.schedule.mapper.PlanMapper;
import com.gdl.schedule.service.PlanService;
import com.gdl.schedule.util.GeneralUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service("planService")
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PageMapper pageMapper;
    @Autowired
    private PlanMapper planMapper;
    @Autowired
    private ItemMapper itemMapper;

    @Override
    public Plan findPlanById(Integer id) {
        return planMapper.findPlanById(id);
    }

    @Override
    public List<Plan> findAllPlans() {
        return null;
    }

    @Override
    public Map<String, Object> findPlansByItemID(HttpServletRequest request, Integer itemID) {
        String pageNow = request.getParameter("pageNow");
        Map<String,Object> map = new LinkedHashMap<String,Object>();
        if (GeneralUtil.isEmpty(pageNow)) {
            pageNow = "1";
        }
        Integer totalCount = pageMapper.getPlanCountByItemID(itemID);
        Page page = new Page(totalCount, Integer.parseInt(pageNow));
        int startPos = page.getStartPos();
        int pageSize = page.getPageSize();
        List<Plan> plan = pageMapper.findPlansByItemID(startPos,pageSize,itemID);
        List<Item> items = new ArrayList<>();
        for (Plan p : plan){
            Item item = itemMapper.findItemById(p.getItemID());
            items.add(item);
        }
        map.put("plan", plan);
        map.put("page", page);
        map.put("items",items);
        return map;
    }

    @Override
    public Map<String,Object> findPlansByUid(HttpServletRequest request, Integer userID) {
        String pageNow = request.getParameter("pageNow");
        Map<String,Object> map = new LinkedHashMap<String,Object>();
        if (GeneralUtil.isEmpty(pageNow)) {
            pageNow = "1";
        }
        Integer totalCount = pageMapper.getPlanCountByUid(userID);
        Page page = new Page(totalCount, Integer.parseInt(pageNow));
        int startPos = page.getStartPos();
        int pageSize = page.getPageSize();
        List<Plan> plan = pageMapper.selectPlansByUserID(startPos,pageSize,userID);
        List<Item> items = new ArrayList<>();
        for (Plan p : plan){
            Item item = itemMapper.findItemById(p.getItemID());
            items.add(item);
        }
        map.put("plan", plan);
        map.put("page", page);
        map.put("items",items);
        return map;
    }

    @Override
    public Map<String, Object> findPlansByKeyword(HttpServletRequest request) {
        String planWork = request.getParameter("planWork");
        System.err.println("planWork:"+planWork);
        String pageNow = request.getParameter("pageNow");
        String it = request.getParameter("itemID");
        Integer itemID = null;
        if (!GeneralUtil.isEmpty(it)){
            itemID = Integer.parseInt(it);
        }
        Map<String,Object> map = new LinkedHashMap<String,Object>();
        if (GeneralUtil.isEmpty(pageNow)) {
            pageNow = "1";
        }
        Integer totalCount = pageMapper.getPlanCountByKeyword(planWork);
        Page page = new Page(totalCount, Integer.parseInt(pageNow));
        int startPos = page.getStartPos();
        int pageSize = page.getPageSize();
        List<Plan> plan = pageMapper.findPlansByKeyword(startPos,pageSize,itemID,planWork);
        List<Item> items = new ArrayList<>();
        for (Plan p : plan){
            Item item = itemMapper.findItemById(p.getItemID());
            items.add(item);
        }
        map.put("plan", plan);
        map.put("page", page);
        map.put("items",items);
        return map;
    }

    @Override
    public Integer insertItem(Plan plan) {
        return planMapper.insertPlan(plan);
    }

    @Override
    public Integer updatePlan(Plan plan) {
        return planMapper.updatePlan(plan);
    }

    @Override
    public Integer deletePlan(Integer id) {
        return planMapper.deletePlan(id);
    }

    @Override
    public Integer deleteMorePlans(List<Integer> planIds) {
        return planMapper.deleteMorePlans(planIds);
    }
}
