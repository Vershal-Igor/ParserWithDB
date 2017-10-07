package com.epam.service.impl;


import com.epam.dao.exception.DAOException;
import com.epam.dao.impl.ArticleDAOImpl;
import com.epam.entity.Article;
import com.epam.service.ArticleService;
import com.epam.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.util.List;

public class ArticleServiceImpl implements ArticleService {
    private static final Logger logger = Logger.getLogger(ArticleServiceImpl.class);
    private ArticleDAOImpl articleDAO;

    @Override
    public List<Article> findAll() throws ServiceException {
        List<Article> articles = null;
        try {
            articles = articleDAO.findAll();
        } catch (DAOException e) {
            logger.error("error while find all articles", e);
            throw new ServiceException("error while find all articles", e);
        }
        return articles;
    }

    @Override
    public Article findById(Long id) throws ServiceException {
        return null;
    }

    @Override
    public void add(Article entity) throws ServiceException {

    }

    @Override
    public void delete(Long id) throws ServiceException {

    }

    public void deleteAll() throws ServiceException {
        try {
            articleDAO.deleteAll(articleDAO.findAll());
        } catch (DAOException e) {
            logger.error("error while delete all articles", e);
            throw new ServiceException("error while delete all articles", e);
        }
    }

    @Override
    public void update(Article entity) throws ServiceException {

    }

    @Override
    public void loadArticles(List<Article> articles) throws ServiceException {
        try {
            articleDAO.loadArticles(articles);
        } catch (DAOException e) {
            logger.error("error while load all articles", e);
            throw new ServiceException("error while load all articles", e);
        }
    }

    @Override
    public void loadArticle(Article article) throws ServiceException {
        try {
            articleDAO.loadArticle(article);
        } catch (DAOException e) {
            logger.error("error while load article", e);
            throw new ServiceException("error while load article", e);
        }
    }

    public void setArticleDAO(ArticleDAOImpl articleDAO) {
        this.articleDAO = articleDAO;
    }
}
