package com.sergeev.datesauction.model.dao;

import com.sergeev.datesauction.model.AbstractModel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by dmitry-sergeev on 27.10.15.
 */

@Repository @Transactional
public interface AbstractDao<T extends AbstractModel, Id extends Number> {

    void persist(T entity);

    void update(T entity);

    T findById(Id id);

    void delete(T entity);

    List<T> getAll();

    void deleteAll();
}
