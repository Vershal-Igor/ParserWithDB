package com.epam.service;




import com.epam.service.exception.ServiceException;

import java.util.List;

public interface GenericService<E, K> {
    List<E> findAll() throws ServiceException;

    E findById(K id) throws ServiceException;

    void add(E entity) throws ServiceException;

    void delete(K id) throws ServiceException;

    void update(E entity) throws ServiceException;
}
