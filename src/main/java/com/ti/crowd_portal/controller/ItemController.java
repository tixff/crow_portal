package com.ti.crowd_portal.controller;

import com.ti.crowd_manager.domain.Item;
import com.ti.crowd_manager.domain.result.ResultData;
import com.ti.crowd_portal.service.ItemSearchService;
import com.ti.crowd_portal.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Ti
 * @date 2019/2/2
 */
@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemSearchService searchService;

    @GetMapping("order")
    public String suport(Model model, Integer itemId) {
        Item item = searchService.searchItemById(itemId);
        model.addAttribute("item", item);
        return "order";
    }

    @PostMapping("pay")
    @ResponseBody
    public ResultData pay(Item item) {
        ResultData resultData = ResultData.createResultData();
        Item updateItem = itemService.updateItemCurrentMoneyAndContibuteNum(item);
        if(updateItem!=null){
            searchService.addItem(updateItem);
            resultData.setMessage("支付成功");
        }else {
            resultData.setMessage("支付失败");
        }
        return resultData;
    }
}
