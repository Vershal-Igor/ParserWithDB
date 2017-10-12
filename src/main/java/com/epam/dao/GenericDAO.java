package com.epam.dao;


import com.epam.dao.exception.DAOException;

import java.util.List;

public interface GenericDAO<E, K> {

    List<E> findAll() throws DAOException;

    void delete(K id) throws DAOException;

    void deleteAll() throws DAOException;

    void update(E entity) throws DAOException;
}
