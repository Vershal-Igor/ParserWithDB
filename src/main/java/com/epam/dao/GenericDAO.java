package com.epam.dao;



import com.epam.dao.exception.DAOException;

import java.util.List;

public interface GenericDAO<E, K> {

    List<E> findAll() throws DAOException;

    E findById(Long id) throws DAOException;

    Long add(E entity) throws DAOException;

    void delete(Long id) throws DAOException;

    void update(E entity) throws DAOException;
}
