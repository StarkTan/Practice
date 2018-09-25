package com.stark.jpa.common.repository;

import com.stark.jpa.common.entity.PageRequest;
import com.stark.jpa.common.entity.PageResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.Map;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

    String test();

    PageResponse pageQuery(PageRequest request);

}
