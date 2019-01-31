package com.ti.crowd_portal.controller;

import com.ti.crowd_manager.domain.Item;
import com.ti.crowd_manager.domain.parameter.PageQuery;
import com.ti.crowd_manager.domain.result.PageResult;
import com.ti.crowd_portal.service.ItemSearchService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Ti
 * @date 2019/1/31
 */

@Controller
@RequestMapping("/search")
public class ItemSearchController {

    @Autowired
    private ItemSearchService searchService;

    @PostMapping
    public String search(Model model, PageQuery query) {
        if (StringUtils.isBlank(query.getQuery())) {
            query.setQuery("*:*");
        }
        PageResult<Item> pageResult = searchService.searchItemByItemTitle(query);
        pageResult.setQuery(query.getQuery());
        model.addAttribute("pageResult", pageResult);
        return "search";
    }

    @GetMapping("page")
    public String searchPage(Model model,String query,Integer currentPage) {
        PageQuery pageQuery = new PageQuery();
        pageQuery.setQuery(query);
        pageQuery.setCurrentPage(currentPage);
        PageResult<Item> pageResult = searchService.searchItemByItemTitle(pageQuery);
        pageResult.setQuery(query);
        model.addAttribute("pageResult", pageResult);
        return "search";
    }
}
