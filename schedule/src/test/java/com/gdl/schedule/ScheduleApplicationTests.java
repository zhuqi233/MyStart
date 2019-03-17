package com.gdl.schedule;

import com.gdl.schedule.entity.Item;
import com.gdl.schedule.entity.Plan;
import com.gdl.schedule.entity.User;
import com.gdl.schedule.mapper.ItemMapper;
import com.gdl.schedule.mapper.PageMapper;
import com.gdl.schedule.mapper.PlanMapper;
import com.gdl.schedule.mapper.UserMapper;
import com.gdl.schedule.util.GeneralUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScheduleApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PlanMapper planMapper;
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private PageMapper pageMapper;
    @Test
    public void contextLoads() {
        List<Plan> plan = pageMapper.findPlansByKeyword(0,10,2,"测试计划");
        for (Plan p:plan){
            System.out.println(p);
        }
        Integer in = pageMapper.getPlanCountByKeyword("测试计划");
        System.err.println(in);
    }
    @Test
    public void testSelectAllPlans(){
       List<Plan> list =  pageMapper.selectPlansByUserID(0,10,2);
       for (Plan p : list){
           System.out.println(p);
       }
    }
    @Test
    public void testIInsert(){
        Item item = new Item();
        item.setItemName("测试项目一号");
        item.setIsEnable(0);
        item.setUserID(2);
        Integer id = itemMapper.insertItem(item);
        System.err.println("项目ID："+id);
    }
    @Test
    public void testPInsert(){
        Plan plan = new Plan();
        plan.setPlanDate(GeneralUtil.formatDate("2018-11-20 08:00:20","yyyy-MM-dd HH:mm:ss"));
        plan.setPlanWork("没啥安排");
        plan.setItemID(1);
        Integer id = planMapper.insertPlan(plan);
        System.err.println("计划id:"+id);
    }
    @Test
    public void testUUpdate() {
        User user = userMapper.findUserByID(3);
        user.setUserName("sans");
        Integer index = userMapper.updateUser(user);
        System.out.println("index:"+index);
    }
    @Test
    public void testUSelect() {
        User user = userMapper.findUserByID(2);
        //User user = userMapper.findUserByUserName("test");
        System.err.println(user);
    }
    @Test
    public void testUInsert() {
        User user = new User();
        Date date = new Date();
        user.setUserName("Leon");
        user.setPassword("123456");
        user.setNickName("里");
        user.setFullName("昂");
        user.setAddDate(date);
        user.setMobilePhone("0111111111");
        user.setState(2);
        user.setType(1);
        user.setRoleID(2);
        System.err.println(user);
        userMapper.insertUser(user);
        Integer id = user.getId();
        System.err.println("ID:"+id);
    }

}
