package com.gdl.schedule.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Plan implements Serializable {

    private static final long serialVersionUID = -145463667971272279L;
    private Integer id;          //计划ID
    private Date planDate;      //计划时间
    private String planWork;    //计划安排
    private String actualWork;  //实际进度
    private Date actualWorkDate;        //实际进度记录时间
    private Integer complete;  //是否完成 0-未完成 1-已完成
    private Date completeDate;         //实际完成时间
    private Integer itemID;     //项目ID
    private Integer userID;     //用户ID

    public Plan() {
    }

    public Plan(Integer id, Date planDate, String planWork, String actualWork, Date actualWorkDate, Integer complete, Date completeDate, Integer itemID) {
        this.id = id;
        this.planDate = planDate;
        this.planWork = planWork;
        this.actualWork = actualWork;
        this.actualWorkDate = actualWorkDate;
        this.complete = complete;
        this.completeDate = completeDate;
        this.itemID = itemID;
    }

    public Plan(Integer id, Date planDate, String planWork, String actualWork, Date actualWorkDate, Integer complete, Date completeDate, Integer itemID, Integer userID) {
        this.id = id;
        this.planDate = planDate;
        this.planWork = planWork;
        this.actualWork = actualWork;
        this.actualWorkDate = actualWorkDate;
        this.complete = complete;
        this.completeDate = completeDate;
        this.itemID = itemID;
        this.userID = userID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getPlanDate() {
        return planDate;
    }

    public void setPlanDate(Date planDate) {
        this.planDate = planDate;
    }

    public String getPlanWork() {
        return planWork;
    }

    public void setPlanWork(String planWork) {
        this.planWork = planWork;
    }

    public String getActualWork() {
        return actualWork;
    }

    public void setActualWork(String actualWork) {
        this.actualWork = actualWork;
    }

    public Date getActualWorkDate() {
        return actualWorkDate;
    }

    public void setActualWorkDate(Date actualWorkDate) {
        this.actualWorkDate = actualWorkDate;
    }

    public Integer getComplete() {
        return complete;
    }

    public void setComplete(Integer complete) {
        this.complete = complete;
    }

    public Date getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }

    public Integer getItemID() {
        return itemID;
    }

    public void setItemID(Integer itemID) {
        this.itemID = itemID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plan plan = (Plan) o;
        return Objects.equals(id, plan.id) &&
                Objects.equals(planDate, plan.planDate) &&
                Objects.equals(planWork, plan.planWork) &&
                Objects.equals(actualWork, plan.actualWork) &&
                Objects.equals(actualWorkDate, plan.actualWorkDate) &&
                Objects.equals(complete, plan.complete) &&
                Objects.equals(completeDate, plan.completeDate) &&
                Objects.equals(itemID, plan.itemID) &&
                Objects.equals(userID, plan.userID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, planDate, planWork, actualWork, actualWorkDate, complete, completeDate, itemID, userID);
    }

    @Override
    public String toString() {
        return "Plan{" +
                "id=" + id +
                ", planDate=" + planDate +
                ", planWork='" + planWork + '\'' +
                ", actualWork='" + actualWork + '\'' +
                ", actualWorkDate=" + actualWorkDate +
                ", complete=" + complete +
                ", completeDate=" + completeDate +
                ", itemID=" + itemID +
                ", userID=" + userID +
                '}';
    }
}
