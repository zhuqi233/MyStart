package com.gdl.schedule.mapper;

import com.gdl.schedule.entity.Item;
import com.gdl.schedule.entity.Plan;

import java.util.List;

public interface ItemMapper {

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
     * 根据项目名称查询项目
     * @param itemName
     * @return
     */
    public Item findItemByItemname(String itemName);

    /**
     * 新建项目
     * @param item
     * @return 新增项目id
     */
    public Integer insertItem(Item item);

    /**
     * 修改项目信息
     * @param item
     * @return  修改的数据数量
     */
    public Integer updateItem(Item item);

    /**
     * 根据id删除项目
     * @param id
     * @return  删除的数据数量
     */
    public Integer deleteItem(Integer id);

}
