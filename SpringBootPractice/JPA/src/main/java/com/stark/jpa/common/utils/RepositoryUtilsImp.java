package com.stark.jpa.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import java.util.*;

public class RepositoryUtilsImp<T> implements RepositoryUtils<T> {

    Logger logger = LoggerFactory.getLogger(RepositoryUtilsImp.class);

    public Specification<T> getConditionSpec(Map<String, Object[]> paramMap) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            for (Map.Entry<String, Object[]> entry : paramMap.entrySet()) {
                String key = entry.getKey();
                Object[] values = entry.getValue();
                String[] arr = key.split("_");
                if (arr.length < 3) {
                    continue;
                }
                String name = arr[0];
                String type = arr[1];
                String method = arr[2];
                if ("s".equals(type)) {
                    switch (method) {
                        case "equal":
                            predicates.add(criteriaBuilder.equal(root.get(name), values[0]));
                            break;
                        case "like":
                            predicates.add(criteriaBuilder.like(root.get(name), "%" + values[0] + "%"));
                            break;
                        case "in":
                            CriteriaBuilder.In<Object> in = criteriaBuilder.in(root.get(name));
                            for (Object value : values) {
                                in = in.value(value);
                            }
                            predicates.add(in);
                            break;
                        case "notNull":
                            predicates.add(criteriaBuilder.isNotNull(root.get(name)));
                            break;
                        case "null":
                            predicates.add(criteriaBuilder.isNull(root.get(name)));
                            break;
                        default:
                            logger.warn("bad params : " + key);
                            break;
                    }
                } else if ("i".equals(type) || "d".equals(type) || "f".equals(type)) {
                    switch (method) {
                        case "equal":
                            predicates.add(criteriaBuilder.equal(root.get(name), values[0]));
                            break;
                        case "in":
                            CriteriaBuilder.In<Object> in = criteriaBuilder.in(root.get(name));
                            for (Object value : values) {
                                in = in.value(value);
                            }
                            predicates.add(in);
                            break;
                        case "between":
                            if ("i".equals(type)) {
                                predicates.add(criteriaBuilder.between(root.get(name), (int) values[0], (int) values[1]));
                            } else if ("d".equals(type)) {
                                predicates.add(criteriaBuilder.between(root.get(name), (double) values[0], (double) values[1]));
                            } else {
                                predicates.add(criteriaBuilder.between(root.get(name), (float) values[0], (float) values[1]));
                            }
                            break;
                        case "lt":
                            if ("i".equals(type)) {
                                predicates.add(criteriaBuilder.lt(root.get(name), (int) values[0]));
                            } else if ("d".equals(type)) {
                                predicates.add(criteriaBuilder.lt(root.get(name), (double) values[0]));
                            } else {
                                predicates.add(criteriaBuilder.lt(root.get(name), (float) values[0]));
                            }
                            break;
                        case "le":
                            if ("i".equals(type)) {
                                predicates.add(criteriaBuilder.le(root.get(name), (int) values[0]));
                            } else if ("d".equals(type)) {
                                predicates.add(criteriaBuilder.le(root.get(name), (double) values[0]));
                            } else {
                                predicates.add(criteriaBuilder.le(root.get(name), (float) values[0]));
                            }
                            break;
                        default:
                            logger.warn("bad params : " + key);
                            break;
                    }
                } else if ("bool".equals(type)) {
                    switch (method) {
                        case "true":
                            predicates.add(criteriaBuilder.isTrue(root.get(name)));
                            break;
                        case "false":
                            predicates.add(criteriaBuilder.isFalse(root.get(name)));
                            break;
                        default:
                            logger.warn("bad params : " + key);
                            break;
                    }
                }
            }
            query.where(predicates.toArray(new Predicate[]{}));
            return query.getRestriction();
        };
    }

    @Override
    public Specification<T> getSortSpec(Map<String, String> sortMap) {
        return (root, query, criteriaBuilder) -> {
            List<Order> orders = new ArrayList<>();
            if (!sortMap.containsKey("id")) {
                sortMap.put("id", "asc");
            }
            for (Map.Entry<String, String> entry : sortMap.entrySet()) {
                String propertyName = entry.getKey();
                String order = entry.getValue();

                if ("asc".equalsIgnoreCase(order)) {
                    orders.add(criteriaBuilder.asc(root.get(propertyName)));
                } else if ("desc".equalsIgnoreCase(order)) {
                    orders.add(criteriaBuilder.desc(root.get(propertyName)));
                }
            }
            query.orderBy(orders);
            return query.getRestriction();
        };
    }
}
