package com.stark.jpa.common.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * 分页请求
 */
@Setter
@Getter
public class PageRequest {

    private int pageSize = 20; //每一页数量

    private int pageNum = 1; //页数

    private Map<String, Object[]> paramMap = new HashMap<>();

    private Map<String, String> sortMap = new HashMap<>();
}
