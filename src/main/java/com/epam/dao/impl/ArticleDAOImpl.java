package com.epam.dao.impl;


import com.epam.dao.ArticleDAO;
import com.epam.dao.exception.DAOException;
import com.epam.entity.Article;
import com.epam.exception.ParserException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import java.util.List;


public class ArticleDAOImpl implements ArticleDAO {


    private SessionFactory sessionFactory;

    public ArticleDAOImpl() {
    }


    @Override
    public List<Article> findAll() throws DAOException {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        List<Article> list = session.createQuery("from Article a ").list();
        session.flush();
        tx.commit();
        return list;
    }

    @Override
    public Article findById(Long id) throws DAOException {
        return null;
    }

    @Override
    public Long add(Article articles) throws DAOException {
        return null;
    }

    @Override
    public void delete(Long id) throws DAOException {

    }

    @Override
    public void update(Article entity) throws DAOException {

    }


    @Override
    public void loadArticles(List<Article> articles) throws DAOException, ParserException {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(articles);
        session.flush();
        tx.commit();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
