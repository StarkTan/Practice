package com.stark.jpa.common;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class BaseRepositoryImp<T, ID> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {
    public BaseRepositoryImp(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.entityManager = entityManager;
        this.entityClass = domainClass;
    }

    @PersistenceContext
    private EntityManager entityManager;

    private Class<T> entityClass;

    @Override
    public String test() {
        return "TEST";
    }
}
