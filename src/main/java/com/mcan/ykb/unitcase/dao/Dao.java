package com.mcan.ykb.unitcase.dao;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
public abstract class Dao<T> {
    private Class< T > clazz;

    @PersistenceContext
    EntityManager entityManager;

    public void setClazz( Class< T > clazzToSet ) {
        this.clazz = clazzToSet;
    }

    public T findOne( Long id ){
        return entityManager.find( clazz, id );
    }
    public List< T > findAll(){
        System.out.println("Class Name : " + clazz.getName());
        return entityManager.createQuery( "from " + clazz.getName() )
                .getResultList();
    }
    @Transactional
    public void save( T entity ){
        entityManager.persist( entity );
    }
    @Transactional
    public void update( T entity ){
        entityManager.merge( entity );
    }
    @Transactional
    public void delete( T entity ){
        entityManager.remove( entity );
    }
    @Transactional
    public void deleteById( Long entityId ){
        T entity = findOne( entityId );
        delete( entity );
    }
}
