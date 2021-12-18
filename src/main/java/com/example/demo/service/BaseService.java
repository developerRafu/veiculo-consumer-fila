package com.example.demo.service;

import java.util.List;
import java.util.Optional;

public interface BaseService<T> {
    Optional<T> findById(Long id);
    List<T> findAll();
    T save(T obj);
    T update(T obj);
    void deleteById(Long id);
}
