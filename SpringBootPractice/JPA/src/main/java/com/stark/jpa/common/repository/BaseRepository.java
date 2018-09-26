package com.stark.jpa.common.repository;

import com.stark.jpa.common.entity.PageRequest;
import com.stark.jpa.common.entity.PageResponse;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

    PageResponse pageQuery(PageRequest request);

    T findLimitOne(Specification<T> specification);


    //equal
    default List<T> equal(String attName, Object value) {
        return findAll((Specification<T>) (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(attName), value));
    }

    //isNull
    default List<T> isNull(String attName) {
        return findAll((Specification<T>) (root, query, criteriaBuilder) -> criteriaBuilder.isNull(root.get(attName)));
    }

    //isNotNull
    default List<T> isNotNull(String attName) {
        return findAll((Specification<T>) (root, query, criteriaBuilder) -> criteriaBuilder.isNotNull(root.get(attName)));
    }

    //isTrue
    default List<T> isTrue(String attName) {
        return findAll((Specification<T>) (root, query, criteriaBuilder) -> criteriaBuilder.isTrue(root.get(attName)));
    }

    //isFalse
    default List<T> isFalse(String attName) {
        return findAll((Specification<T>) (root, query, criteriaBuilder) -> criteriaBuilder.isFalse(root.get(attName)));
    }

    //between long
    default List<T> betweenLong(String attName, long le, long lt) {
        return findAll((Specification<T>) (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get(attName), le, lt));
    }


}
