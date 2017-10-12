package com.epam.dao;


import com.epam.dao.exception.DAOException;
import com.epam.entity.Article;

import java.util.List;

public interface ArticleDAO extends GenericDAO<Article, String> {
    void saveArticles(List<Article> articles) throws DAOException;

    Article findByTitle(String title) throws DAOException;

    void deleteAllFromList(List<Article> articles) throws DAOException;
}
