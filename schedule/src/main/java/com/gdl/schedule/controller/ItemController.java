package com.gdl.schedule.controller;

import com.gdl.schedule.entity.Item;
import com.gdl.schedule.entity.ResponseResult;
import com.gdl.schedule.service.ItemService;
import com.gdl.schedule.util.GeneralUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;
    @RequestMapping("/product-brand.html")
    public String product_brand() { return "product-brand"; }
    @RequestMapping("/product-category.html")
    public String product_category() { return "product-category"; }
    @RequestMapping("/product-category-add.html")
    public String product_category_add() { return "product-category-add"; }
    @RequestMapping("/product-category-update.html")
    public String product_category_update() { return "product-category-update"; }
    @RequestMapping("/product-list.html")
    public String product_list() { return "product-list.html"; }
    @RequestMapping("/product-add.html")
    public String product_add() { return "product-add"; }

    @RequestMapping("/handle_item_add")
    public String handle_item_add(@RequestParam(value = "product-category-name")String itemname,
                                  @RequestParam(value = "isEnable")Integer isEnable,
                                  HttpSession session){
        Integer userID = (Integer) session.getAttribute("uid");
        if (!GeneralUtil.isEmpty(itemname))
        itemService.insertItem(itemname,isEnable,userID);
        return "product-category-add";
    }

    @RequestMapping("/item_handleUpdate")
    public String handle_item_handleUpdate(@RequestParam(value = "product-category-name")String itemname,
            @RequestParam(value = "isEnable")Integer isEnable,@RequestParam(value = "id")Integer id){
        System.out.println(id+"====="+itemname+"===="+isEnable);
        if (!GeneralUtil.isEmpty(itemname)&&id!=null){
            Integer in = itemService.updateItem(itemname,isEnable,id);
            System.out.println("修改数据:"+in);
        }
        return "product-category-update";
    }

    @RequestMapping("/handle_item_query")
    @ResponseBody
    public ResponseResult handle_item_query(HttpSession session){
        ResponseResult rr;
        Integer userID = (Integer) session.getAttribute("uid");
        List<Item> list = itemService.findItemByUid(userID);
        if(GeneralUtil.isEmpty(list)){
            rr = new ResponseResult(0,"该用户无项目记录");
        }else {
            rr = new ResponseResult(1,list);
        }
        return rr;
    }

    @RequestMapping("/handle_findItemByID")
    @ResponseBody
    public ResponseResult handle_findItemByID(@RequestParam("id") Integer id){
        ResponseResult rr;
        Item item = itemService.findItemById(id);
        if (item==null){
            rr = new ResponseResult(0,"无项目数据");
        }else {
            rr = new ResponseResult(1,item);
        }
        return rr;
    }

}
