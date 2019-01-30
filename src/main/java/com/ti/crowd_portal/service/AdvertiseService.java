package com.ti.crowd_portal.service;

import com.ti.crowd_manager.domain.Advertisement;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Ti
 * @date 2019/1/30
 */
public interface AdvertiseService {

    HashMap<String,ArrayList<Advertisement>> getAdvertise();
}
