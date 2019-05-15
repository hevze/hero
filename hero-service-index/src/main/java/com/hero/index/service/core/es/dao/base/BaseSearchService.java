package com.hero.index.service.core.es.dao.base;

import org.elasticsearch.index.query.QueryBuilders;

import java.io.Serializable;
import java.util.NoSuchElementException;

public abstract class BaseSearchService<E, ID extends Serializable, R extends BaseSearchRepository<E, ID>> { //spring 4.X 支持泛型注入


    protected abstract R getRepository();


    public E getById(ID id) {
        return getRepository().findById(id).get();
    }

    public Iterable<E> listAll() {
        return getRepository().findAll();
    }

    public void save(E data) {
        getRepository().save(data);
    }

    public void delete(E data) {
        getRepository().delete(data);
    }

    public void deleteById(ID id) {
        getRepository().deleteById(id);
    }

    public E getByKey(String fieldName, Object value) {
        try {
            return getRepository().search(QueryBuilders.matchQuery(fieldName, value)).iterator().next();
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}
