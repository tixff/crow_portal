package com.ti.crowd_manager.domain.result;

import lombok.Data;

/**
 * @author Ti
 * @date 2019/1/24
 */
@Data
public class ResultData {
    public static final String FAIL="500";
    public static final String SUCCESS = "200";
    private String status;
    private String message;
    private Object data;

    private ResultData() {
    }

    public static ResultData createResultData() {
        return new ResultData();
    }
}
