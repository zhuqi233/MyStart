package com.gdl.schedule.entity;

import java.io.Serializable;
import java.util.Objects;

public class Role implements Serializable {

    private static final long serialVersionUID = -2928234407788378478L;
    private Integer id;               //角色ID
    private String names;             //名称
    private Integer sorting;          //顺序
    private String menus;             //菜单权限
    private String articleMenus;     //栏目权限

    public Role() { }

    public Role(Integer id, String names, Integer sorting, String menus, String articleMenus) {
        this.id = id;
        this.names = names;
        this.sorting = sorting;
        this.menus = menus;
        this.articleMenus = articleMenus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public Integer getSorting() {
        return sorting;
    }

    public void setSorting(Integer sorting) {
        this.sorting = sorting;
    }

    public String getMenus() {
        return menus;
    }

    public void setMenus(String menus) {
        this.menus = menus;
    }

    public String getArticleMenus() {
        return articleMenus;
    }

    public void setArticleMenus(String articleMenus) {
        this.articleMenus = articleMenus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) &&
                Objects.equals(names, role.names) &&
                Objects.equals(sorting, role.sorting) &&
                Objects.equals(menus, role.menus) &&
                Objects.equals(articleMenus, role.articleMenus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, names, sorting, menus, articleMenus);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", names='" + names + '\'' +
                ", sorting=" + sorting +
                ", menus='" + menus + '\'' +
                ", articleMenus='" + articleMenus + '\'' +
                '}';
    }
}
