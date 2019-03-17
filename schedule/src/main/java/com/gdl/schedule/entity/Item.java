package com.gdl.schedule.entity;

import java.io.Serializable;
import java.util.Objects;

public class Item implements Serializable {
    private static final long serialVersionUID = 7226103323194227353L;
    private Integer id; //项目ID
    private String itemName;    //项目名
    private Integer sorting;    //顺序
    private Integer isEnable;   //是否启用 0-未启用 1-已启用
    private Integer userID;     //用户ID

    public Item() {
    }

    public Item(Integer id, String itemName, Integer sorting, Integer isEnable, Integer userID) {
        this.id = id;
        this.itemName = itemName;
        this.sorting = sorting;
        this.isEnable = isEnable;
        this.userID = userID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getSorting() {
        return sorting;
    }

    public void setSorting(Integer sorting) {
        this.sorting = sorting;
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
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
        Item item = (Item) o;
        return Objects.equals(id, item.id) &&
                Objects.equals(itemName, item.itemName) &&
                Objects.equals(sorting, item.sorting) &&
                Objects.equals(isEnable, item.isEnable) &&
                Objects.equals(userID, item.userID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, itemName, sorting, isEnable, userID);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", sorting=" + sorting +
                ", isEnable=" + isEnable +
                ", userID=" + userID +
                '}';
    }
}
