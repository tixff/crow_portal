package com.ti.crowd_manager.domain.result;

import lombok.Data;

import java.util.ArrayList;

/**
 * @author Ti
 * @date 2019/1/28
 */
@Data
public class PageResult<T> {
    private Integer currentPage;
    private Integer totalCount;
    private Integer totalPage;
    private ArrayList<T> data;
    private String query;

}
