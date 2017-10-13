package com.epam.dao.impl;


import com.epam.dao.ArticleDAO;
import com.epam.dao.exception.DAOException;
import com.epam.entity.Article;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {ObjectNotFoundException.class,
        ConstraintViolationException.class})
public class ArticleDAOImpl implements ArticleDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Article> findAll() throws DAOException {
        Session session = sessionFactory.getCurrentSession();
        List<Article> list = session.createQuery("from Article a ").list();
        return list;
    }

    @Override
    public Article findByTitle(String title) throws DAOException {
        Session session = sessionFactory.getCurrentSession();
        Article article = session.get(Article.class, title);
        return article;
    }

    @Override
    public void delete(String title) throws DAOException {
        Session session = sessionFactory.getCurrentSession();
        Article article = session.get(Article.class, title);
        session.delete(article);
    }


    @Override
    public void deleteAllFromList(List<Article> articles) throws DAOException {
        Session session = sessionFactory.getCurrentSession();
        articles.forEach(session::delete);
    }

    @Override
    public void deleteAll() throws DAOException {
        Session session = sessionFactory.getCurrentSession();
        findAll().forEach(session::delete);
    }

    @Override
    public void update(Article article) throws DAOException {
        Session session = sessionFactory.getCurrentSession();
        session.update(article);
    }

    @Override
    public void saveArticles(List<Article> articles) throws DAOException {
        Session session = sessionFactory.getCurrentSession();
        articles.forEach(session::save);
    }

    @Override
    public void save(Article article) throws DAOException {
        Session session = sessionFactory.getCurrentSession();
        session.save(article);
    }

}
