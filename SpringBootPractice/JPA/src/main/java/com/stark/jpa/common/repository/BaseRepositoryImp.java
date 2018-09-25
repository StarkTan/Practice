package com.stark.jpa.common.repository;

import com.stark.jpa.common.entity.PageRequest;
import com.stark.jpa.common.entity.PageResponse;
import com.stark.jpa.common.utils.RepositoryUtils;
import com.stark.jpa.common.utils.RepositoryUtilsImp;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class BaseRepositoryImp<T, ID> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {
    public BaseRepositoryImp(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.entityManager = entityManager;
        this.entityClass = domainClass;
    }

    @PersistenceContext
    private EntityManager entityManager;

    private RepositoryUtils<T> utils = new RepositoryUtilsImp<>();

    private Class<T> entityClass;

    @Override
    public PageResponse<T> pageQuery(PageRequest request) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = criteriaBuilder.createQuery(entityClass);
        Root<T> root = cq.from(entityClass);

        //查询条件
        Specification<T> conditionSpec = utils.getConditionSpec(request.getParamMap());
        conditionSpec = conditionSpec.and(utils.getSortSpec(request.getSortMap()));
        //获取总数
        long total = count(conditionSpec);

        //查询生成
        cq.select(root);
        cq.where(conditionSpec.toPredicate(root, cq, criteriaBuilder));
        Query query = entityManager.createQuery(cq);
        //分页操作
        query.setFirstResult((request.getPageNum() - 1) * request.getPageSize());
        query.setMaxResults(request.getPageSize());
        int firstResult = query.getFirstResult();
        List<T> resultList = total > firstResult ? (List<T>) query.getResultList() : new ArrayList<>();

        return new PageResponse<>(request, resultList, total);
    }
}
