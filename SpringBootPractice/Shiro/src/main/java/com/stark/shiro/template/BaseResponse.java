package com.stark.shiro.template;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Controller的基础返回类
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {
    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 说明
     */
    private String msg;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 分页时候的总数
     */
    private Integer total;

}
