package com.epam.service.impl;


import com.epam.dao.exception.DAOException;
import com.epam.dao.impl.ArticleDAOImpl;
import com.epam.entity.Article;
import com.epam.service.ArticleService;
import com.epam.service.exception.ServiceException;
import org.apache.log4j.Logger;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.ResourceBundle;


/*@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {ObjectNotFoundException.class,
        ConstraintViolationException.class})*/
public class ArticleServiceImpl implements ArticleService {
    private static final Logger logger = Logger.getLogger(ArticleServiceImpl.class);
    private static final ResourceBundle rb = ResourceBundle.getBundle("properties/common");

    private ArticleDAOImpl articleDAO;

    public void setArticleDAO(ArticleDAOImpl articleDAO) {
        this.articleDAO = articleDAO;
    }

    @Override
    public List<Article> findAll() throws ServiceException {
        List<Article> articles;
        try {
            articles = articleDAO.findAll();
        } catch (DAOException e) {
            logger.error(rb.getString("ERR_FIND_ALL"), e);
            throw new ServiceException(rb.getString("ERR_FIND_ALL"), e);
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
            logger.error(rb.getString("ERR_FIND_BY_TITLE"), e);
            throw new ServiceException(rb.getString("ERR_FIND_BY_TITLE"), e);
        }
        return article;
    }

    @Override
    public void delete(String id) throws ServiceException {
        try {
            articleDAO.delete(id);
        } catch (DAOException e) {
            logger.error(rb.getString("ERR_DEL_BY_ID"), e);
            throw new ServiceException(rb.getString("ERR_DEL_BY_ID"), e);
        }
    }

    @Override
    public void deleteAllFromList(List<Article> articles) throws ServiceException {
        try {
            articleDAO.deleteAllFromList(articles);
        } catch (DAOException e) {
            logger.error(rb.getString("ERR_DEL_ALL_FROM_LIST"), e);
            throw new ServiceException(rb.getString("ERR_DEL_ALL_FROM_LIST"), e);
        }
    }

    @Override
    public void deleteAll() throws ServiceException {
        try {
            articleDAO.deleteAll();
        } catch (DAOException e) {
            logger.error(rb.getString("ERR_DEL_ALL"), e);
            throw new ServiceException(rb.getString("ERR_DEL_ALL"), e);
        }
    }

    @Override
    public void update(Article article) throws ServiceException {
        try {
            articleDAO.update(article);
        } catch (DAOException e) {
            logger.error(rb.getString("ERR_UPDATE"), e);
            throw new ServiceException(rb.getString("ERR_UPDATE"), e);
        }
    }

    @Override
    public void saveArticles(List<Article> articles) throws ServiceException {
        try {
            for (Article article : articles) {
                Article inDatabase = articleDAO.findByTitle(article.getTitle());
                if (inDatabase == null) {
                    articleDAO.save((article));
                } else {
                    logger.info("\"" + article.getTitle() + "\"" + " Article is already in the database");
                }
            }
        } catch (DAOException e) {
            logger.error(rb.getString("ERR_LOAD_ALL"), e);
            throw new ServiceException(rb.getString("ERR_LOAD_ALL"), e);
        }
    }

    @Override
    public void save(Article article) throws ServiceException {
        try {
            Article inDatabase = articleDAO.findByTitle(article.getTitle());
            if (inDatabase == null) {
                articleDAO.save(article);
            } else {
                logger.info("\"" + article.getTitle() + "\" Article is already in the database");
            }
        } catch (DAOException e) {
            logger.error(rb.getString("ERR_LOAD"), e);
            throw new ServiceException(rb.getString("ERR_LOAD"), e);
        }
    }

}
