package com.stark.jpa.common.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 分页请求的返回
 */
@Getter
@Setter
public class PageResponse<T> {

    public PageResponse(PageRequest request, List<T> data, long total) {
        this.pageNum = request.getPageNum();
        this.pageSize = request.getPageSize();
        this.data = data;
        this.total = total;
    }

    private int pageSize; //每一页数量

    private long total; //数据总量

    private int pageNum; //页数

    private List<T> data;

    @Override
    public String toString() {
        return "pageSize: " + pageSize + " totalNum: " + total + " pageNum: " + pageNum + " dataSize: " + data.size();
    }
}
