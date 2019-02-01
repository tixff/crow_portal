package com.ti.crowd_portal.controller;

import com.ti.crowd_manager.domain.Advertisement;
import com.ti.crowd_manager.domain.Item;
import com.ti.crowd_portal.service.AdvertiseService;
import com.ti.crowd_portal.service.ItemSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Ti
 * @date 2019/1/30
 */
@Controller
public class IndexController {

    @Autowired
    private AdvertiseService service;

    @Autowired
    private ItemSearchService searchService;
    @GetMapping("/")
    public String index(Model model) {
        HashMap<String, ArrayList<Advertisement>> advertise = service.getAdvertise();
        ArrayList<Advertisement> minors = advertise.get("minor");
        ArrayList<Advertisement> mains = advertise.get("main");
        ArrayList<Item> items = searchService.searchHotItem();
        model.addAttribute("items",items);
        model.addAttribute("minors",minors);
                model.addAttribute("mains",mains);
        return "index";
    }
}
