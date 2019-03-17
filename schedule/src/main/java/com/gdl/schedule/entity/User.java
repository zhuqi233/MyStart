package com.gdl.schedule.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class User implements Serializable {

    private static final long serialVersionUID = 8003678251760839595L;
    private Integer id;             //用户id
    private String userName;        //用户名
    private String password;        //密码
    private String headPortrait;    //头像
    private String nickName;        //昵称
    private String fullName;        //姓名
    private Date addDate;           //注册时间
    private String mobilePhone;     //手机
    private Date lastLoginDate;     //最后登录时间
    private Integer state;          //状态  1未审核  2已审核  3禁用  4被标记删除
    private Integer type;           //用户类型  1导入  2申请
    private Integer roleID;         //角色ID  1超级管理员  2用户

    public User() { }

    public User(Integer id, String userName, String password, String headPortrait, String nickName, String fullName, Date addDate, String mobilePhone, Date lastLoginDate, Integer state, Integer type, Integer roleID) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.headPortrait = headPortrait;
        this.nickName = nickName;
        this.fullName = fullName;
        this.addDate = addDate;
        this.mobilePhone = mobilePhone;
        this.lastLoginDate = lastLoginDate;
        this.state = state;
        this.type = type;
        this.roleID = roleID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(password, user.password) &&
                Objects.equals(headPortrait, user.headPortrait) &&
                Objects.equals(nickName, user.nickName) &&
                Objects.equals(fullName, user.fullName) &&
                Objects.equals(addDate, user.addDate) &&
                Objects.equals(mobilePhone, user.mobilePhone) &&
                Objects.equals(lastLoginDate, user.lastLoginDate) &&
                Objects.equals(state, user.state) &&
                Objects.equals(type, user.type) &&
                Objects.equals(roleID, user.roleID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, password, headPortrait, nickName, fullName, addDate, mobilePhone, lastLoginDate, state, type, roleID);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", headPortrait='" + headPortrait + '\'' +
                ", nickName='" + nickName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", addDate=" + addDate +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", lastLoginDate=" + lastLoginDate +
                ", state=" + state +
                ", type=" + type +
                ", roleID=" + roleID +
                '}';
    }
}
