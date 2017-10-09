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
        List<Article> articles;
        try {
            articles = articleDAO.findAll();
        } catch (DAOException e) {
            logger.error("error while find all articles", e);
            throw new ServiceException("error while find all articles", e);
        }
        return articles;
    }

    @Override
    public Article findByTitle(String title) throws ServiceException {
        Article article;
        try {
            article = articleDAO.findByTitle(title);
            logger.info("Article with title \"" + article.getTitle() + "\" was find: " + article);
        } catch (DAOException e) {
            logger.error("error while find by title", e);
            throw new ServiceException("error while find by title", e);
        }
        return article;
    }

    @Override
    public void delete(String id) throws ServiceException {
        try {
            articleDAO.delete(id);
        } catch (DAOException e) {
            logger.error("error while delete Article by id", e);
            throw new ServiceException("error while delete Article by id", e);
        }
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
    public void update(Article article) throws ServiceException {
        try {
            articleDAO.update(article);
        } catch (DAOException e) {
            logger.error("error while update article", e);
            throw new ServiceException("error while update article", e);
        }
    }

    @Override
    public void loadArticles(List<Article> articles) throws ServiceException {
        try {
            for (Article article : articles) {
                Article inDatabase = articleDAO.findByTitle(article.getTitle());
                if (inDatabase == null) {
                    articleDAO.loadArticle((article));
                } else {
                    logger.info("\"" + article.getTitle() + "\"" + " Article is already in the database");
                }
            }
        } catch (DAOException e) {
            logger.error("error while load all articles", e);
            throw new ServiceException("error while load all articles", e);
        }
    }

    @Override
    public void loadArticle(Article article) throws ServiceException {
        try {
            Article inDatabase = articleDAO.findByTitle(article.getTitle());
            if (inDatabase == null) {
                articleDAO.loadArticle(article);
            } else {
                logger.info("\"" + article.getTitle() + "\" Article is already in the database");
            }
        } catch (DAOException e) {
            logger.error("error while load article", e);
            throw new ServiceException("error while load article", e);
        }
    }

    public void setArticleDAO(ArticleDAOImpl articleDAO) {
        this.articleDAO = articleDAO;
    }
}
