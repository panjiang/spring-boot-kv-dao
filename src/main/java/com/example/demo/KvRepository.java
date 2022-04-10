package com.example.demo;

public interface KvRepository<T, ID> {
    void init(Class<T> clazz);

    <S extends T> void save(S entity);

    T getById(ID id);
}
