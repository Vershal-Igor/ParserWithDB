package com.epam.service;


import com.epam.entity.Article;
import com.epam.service.exception.ServiceException;

import java.util.List;

public interface ArticleService extends GenericService<Article, String> {
    void saveArticles(List<Article> articles) throws ServiceException;

    Article findByTitle(String title) throws ServiceException;

    void deleteAllFromList(List<Article> articles) throws ServiceException;
}
