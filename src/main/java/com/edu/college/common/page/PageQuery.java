package com.edu.college.common.page;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

public class PageQuery {

    @Getter
    @Setter
    @Min(value = 1, message = "当前页码不合法")
    @ApiModelProperty("当前页码,从1开始(默认1)")
    private int pageNo = 1;

    @Getter
    @Setter
    @Min(value = 1, message = "每页展示数量不合法")
    @ApiModelProperty("每页展示数量(默认10)")
    private int pageSize = 10;

    public int getOffset() {
        return (pageNo - 1) * pageSize;
    }
}
