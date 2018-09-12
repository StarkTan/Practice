package com.stark.jpa.common;

import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;

import javax.persistence.EntityManager;
import java.io.Serializable;

public class BaseRepositoryFactory<T, I extends Serializable> extends JpaRepositoryFactory {

    private final EntityManager em;

    public BaseRepositoryFactory(EntityManager em) {
        super(em);
        this.em = em;
    }

    @Override
    protected Object getTargetRepository(RepositoryInformation information) {

        return new BaseRepositoryImp<T, I>((Class<T>) information.getDomainType(), em);
    }

    @Override
    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
        return BaseRepositoryImp.class;
    }
}
