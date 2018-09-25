package com.stark.jpa.common.repository;

import com.stark.jpa.common.entity.PageRequest;
import com.stark.jpa.common.entity.PageResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

    PageResponse pageQuery(PageRequest request);

}
