package com.stark.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class BaseService<T, D extends JpaRepository<T, String>> {

    @Autowired
    private D repository;

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

    public T getById(String id) {
        return repository.findById(id).orElse(null);
    }

    public void flush() {
        getRepository().flush();
    }
}
