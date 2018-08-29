package com.stark.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.util.List;

public abstract class BaseService<T, D extends JpaRepository<T, String>> {

    @Autowired
    private D repository;

    @Resource
    private RedisTemplate<Object, Object> redis;

    protected D getRepository() {
        return repository;
    }

    public List<T> getAll() {
        return repository.findAll();
    }

    public T save(T entity) {
        return repository.save(entity);
    }

    public void delete(T entity) {
        repository.delete(entity);
    }

    @SuppressWarnings("unchecked")
    public T getById(String id) {
        assert (null != id);
        ValueOperations<Object, Object> operations = redis.opsForValue();
        Boolean exist = redis.hasKey(id);
        if (exist == null) {
            return repository.findById(id).orElse(null);
        }
        if (exist) {
            return (T) operations.get(id);
        }
        T entity = repository.findById(id).orElse(null);
        if (null != entity) {
            operations.set(id, entity);
        }
        return entity;
    }

    public void flush() {
        getRepository().flush();
    }
}
