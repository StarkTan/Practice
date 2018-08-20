package com.stark.swagger2.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * API接口的基础返回类
 */
@ApiModel(value = "BaseResponse", description = "API接口的返回对象")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {
    /**
     * 是否成功
     */
    @ApiModelProperty(value = "是否成功", name = "success", example = "true", required = true)
    private boolean success;

    /**
     * 说明
     */
    @ApiModelProperty(value = "返回的详细说明", name = "msg", example = "成功")
    private String msg;

    /**
     * 返回数据
     */
    @ApiModelProperty(value = "返回的数据", name = "data")
    private T data;

}
