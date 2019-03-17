package com.gdl.schedule.service;

import com.gdl.schedule.entity.Item;
import com.gdl.schedule.entity.User;

import java.util.List;

public interface ItemService {
    /**
     * 根据id查询项目
     * @param id
     * @return Item对象
     */
    public Item findItemById(Integer id);

    /**
     * 根据用户ID查询项目
     * @param userID
     * @return  Item对象
     */
    public List<Item> findItemByUid(Integer userID);

    /**
     * 根据项目名称查询项目id
     * @param itemName
     * @return
     */
    public Integer findItemIdByItemname(String itemName);

    /**
     * 新建项目
     * @param itemname,isEnable (项目名称，状态)
     * @return 新增项目id
     */
    public Integer insertItem(String itemname,Integer isEnable,Integer userID);

    /**
     * 修改项目信息
     * @param itemname,isEnable,id
     * @return  修改的数据数量
     */
    public Integer updateItem(String itemname,Integer isEnable,Integer id);

    /**
     * 根据id删除项目
     * @param id
     * @return  删除的数据数量
     */
    public Integer deleteItem(Integer id);
}
