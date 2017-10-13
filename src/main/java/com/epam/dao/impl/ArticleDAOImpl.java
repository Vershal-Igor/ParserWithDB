package com.epam.dao.impl;


import com.epam.dao.ArticleDAO;
import com.epam.dao.exception.DAOException;
import com.epam.entity.Article;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/*@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {ObjectNotFoundException.class,
        ConstraintViolationException.class})*/
public class ArticleDAOImpl implements ArticleDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Article> findAll() throws DAOException {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        List<Article> list = session.createQuery("from Article a ").list();
        session.flush();
        tx.commit();
        session.close();
        return list;
    }

    @Override
    public Article findByTitle(String title) throws DAOException {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Article article = session.get(Article.class, title);
        session.flush();
        tx.commit();
        session.close();
        return article;
    }

    @Override
    public void delete(String title) throws DAOException {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Article article = session.get(Article.class, title);
        session.delete(article);
        session.flush();
        tx.commit();
        session.close();
    }


    @Override
    public void deleteAllFromList(List<Article> articles) throws DAOException {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        articles.forEach(session::delete);
        session.flush();
        tx.commit();
        session.close();
    }

    @Override
    public void deleteAll() throws DAOException {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        findAll().forEach(session::delete);
        session.flush();
        tx.commit();
        session.close();
    }

    @Override
    public void update(Article article) throws DAOException {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(article);
        session.flush();
        tx.commit();
        session.close();
    }

    @Override
    public void saveArticles(List<Article> articles) throws DAOException {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        articles.forEach(session::save);
        session.flush();
        tx.commit();
        session.close();
    }

    @Override
    public void save(Article article) throws DAOException {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(article);
        session.flush();
        tx.commit();
        session.close();
    }

}
