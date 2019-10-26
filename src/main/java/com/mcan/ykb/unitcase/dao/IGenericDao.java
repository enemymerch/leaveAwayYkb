package com.mcan.ykb.unitcase.dao;

import java.util.List;

public interface IGenericDao<T>  {
    void setClazz(Class<T> clazz);

    T findOne(Long id);

    List<T> findAll();

    void save(final T entity);

    void update(final T entity);

    void delete(final T entity);

    void deleteById(Long entityId);
}
