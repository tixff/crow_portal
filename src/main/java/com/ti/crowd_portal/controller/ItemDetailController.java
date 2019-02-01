package com.ti.crowd_portal.controller;

import com.ti.crowd_manager.domain.Item;
import com.ti.crowd_manager.domain.ItemDetail;
import com.ti.crowd_portal.service.ItemDetailService;
import com.ti.crowd_portal.service.ItemSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Ti
 * @date 2019/2/1
 */

@Controller
@RequestMapping("/itemDetail")
public class ItemDetailController {

    @Autowired
    private ItemDetailService itemDetailService;
    @Autowired
    private ItemSearchService searchService;

    @GetMapping("/find")
    public String findByItemId(Model model, Integer itemId) {
        ItemDetail itemDetail = itemDetailService.findByItemId(itemId);
        Item item = searchService.searchItemById(itemId);
        item.setItemDetail(itemDetail.getContent());
        model.addAttribute("item",item);
        return "detail";
    }
}
