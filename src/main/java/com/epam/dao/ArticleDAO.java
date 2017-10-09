package com.epam.dao;


import com.epam.dao.exception.DAOException;
import com.epam.entity.Article;

import java.util.List;

public interface ArticleDAO extends GenericDAO<Article, String> {
    void loadArticles(List<Article> articles) throws DAOException;

    void loadArticle(Article article) throws DAOException;

    Article findByTitle(String title) throws DAOException;

    boolean checkArticle(Article article) throws DAOException;
}
