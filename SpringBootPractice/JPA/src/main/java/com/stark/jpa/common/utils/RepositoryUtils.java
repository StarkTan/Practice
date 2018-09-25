package com.stark.jpa.common.utils;

import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Map;

public interface RepositoryUtils<T> {

    /**
     * propertyName_dataType_condition : data
     * s, i, d, f, bool
     * equal
     */
    Specification<T> getConditionSpec(Map<String, Object[]> paramMap);

    /**
     * propertyName : order
     */
    Specification<T> getSortSpec(Map<String, String> sortMap);

}
