package com.ti.crowd_portal.service;

import com.ti.crowd_manager.domain.ItemDetail;

/**
 * @author Ti
 * @date 2019/1/27
 */
public interface ItemDetailService {

    ItemDetail findByItemId(Integer id);

}
