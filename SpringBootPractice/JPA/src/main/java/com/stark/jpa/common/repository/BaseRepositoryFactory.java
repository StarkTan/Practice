package com.stark.jpa.common.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;

import javax.persistence.EntityManager;
import java.io.Serializable;

public class BaseRepositoryFactory<T, I extends Serializable> extends JpaRepositoryFactory {

    BaseRepositoryFactory(EntityManager em) {
        super(em);
    }

    @Override
    protected JpaRepositoryImplementation<?, ?> getTargetRepository(RepositoryInformation information, EntityManager em) {

        return new BaseRepositoryImp<T, I>((Class<T>) information.getDomainType(), em);
    }

    @Override
    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
        return BaseRepositoryImp.class;
    }
}
