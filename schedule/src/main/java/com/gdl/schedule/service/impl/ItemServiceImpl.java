package com.gdl.schedule.service.impl;

import com.gdl.schedule.entity.Item;
import com.gdl.schedule.mapper.ItemMapper;
import com.gdl.schedule.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("itemService")
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemMapper itemMapper;

    @Override
    public Item findItemById(Integer id) {
        return itemMapper.findItemById(id);
    }

    @Override
    public List<Item> findItemByUid(Integer userID) {

        return itemMapper.findItemByUid(userID);
    }

    @Override
    public Integer findItemIdByItemname(String itemName) {
        Item item = itemMapper.findItemByItemname(itemName);
        Integer id = item.getId();
        return id;
    }

    @Override
    public Integer insertItem(String itemname,Integer isEnable,Integer userID) {
        Item item = new Item();
        item.setItemName(itemname);
        item.setIsEnable(isEnable);
        item.setUserID(userID);
        itemMapper.insertItem(item);
        Integer id = item.getId();
        return id;
    }

    @Override
    public Integer updateItem(String itemname,Integer isEnable,Integer id) {
        Item item = itemMapper.findItemById(id);
        item.setItemName(itemname);
        item.setIsEnable(isEnable);
        Integer index = itemMapper.updateItem(item);
        return index;
    }

    @Override
    public Integer deleteItem(Integer id) {
        return itemMapper.deleteItem(id);
    }
}
