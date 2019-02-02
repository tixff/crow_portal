package com.ti.crowd_portal.service;

import com.ti.crowd_manager.domain.Item;
import com.ti.crowd_manager.domain.parameter.PageQuery;
import com.ti.crowd_manager.domain.result.PageResult;

import java.util.ArrayList;

public interface ItemSearchService {

    PageResult<Item> searchItemByItemTitle(PageQuery query);

    Item searchItemById(Integer id);

    ArrayList<Item> searchHotItem();

    void addItem(Item item);
}
