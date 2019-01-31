package com.ti.crowd_manager.domain.parameter;

import lombok.Data;

/**
 * @author Ti
 * @date 2019/1/28
 */
@Data
public class PageQuery {
    private String query;
    private Integer rows = 12;
    private Integer currentPage = 1;
}
