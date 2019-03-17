package com.gdl.schedule.entity;

import java.io.Serializable;
import java.util.Objects;

public class Menu implements Serializable {

    private static final long serialVersionUID = -57774856863054761L;
    private Integer id;         //菜单ID
    private Integer fid;        //父ID
    private String names;       //名称
    private String icon;        //图标
    private String url;         //地址
    private boolean isEnable;  //是否显示
    private Integer sorting;    //顺序
    private String remark;      //备注

    public Menu() { }

    public Menu(Integer id, Integer fid, String names, String icon, String url, boolean isEnable, Integer sorting, String remark) {
        this.id = id;
        this.fid = fid;
        this.names = names;
        this.icon = icon;
        this.url = url;
        this.isEnable = isEnable;
        this.sorting = sorting;
        this.remark = remark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }

    public Integer getSorting() {
        return sorting;
    }

    public void setSorting(Integer sorting) {
        this.sorting = sorting;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return isEnable == menu.isEnable &&
                Objects.equals(id, menu.id) &&
                Objects.equals(fid, menu.fid) &&
                Objects.equals(names, menu.names) &&
                Objects.equals(icon, menu.icon) &&
                Objects.equals(url, menu.url) &&
                Objects.equals(sorting, menu.sorting) &&
                Objects.equals(remark, menu.remark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fid, names, icon, url, isEnable, sorting, remark);
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", fid=" + fid +
                ", names='" + names + '\'' +
                ", icon='" + icon + '\'' +
                ", url='" + url + '\'' +
                ", isEnable=" + isEnable +
                ", sorting=" + sorting +
                ", remark='" + remark + '\'' +
                '}';
    }
}
