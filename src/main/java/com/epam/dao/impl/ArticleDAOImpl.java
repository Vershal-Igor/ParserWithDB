package com.epam.dao.impl;


import com.epam.dao.ArticleDAO;
import com.epam.dao.exception.DAOException;
import com.epam.entity.Article;
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
        session.close();
        return list;
    }

    @Override
    public Article findByTitle(String title) throws DAOException {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Article article = (Article) session.get(Article.class, title);
        session.flush();
        tx.commit();
        session.close();
        return article;
    }

    @Override
    public void delete(String id) throws DAOException {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Article article = (Article) session.get(Article.class, id);
        session.delete(article);
        session.flush();
        tx.commit();
        session.close();
    }

    public void deleteAll(List<Article> articles) throws DAOException {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        articles.forEach(session::delete);
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
    public void loadArticles(List<Article> articles) throws DAOException {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        articles.forEach(session::save);
        session.flush();
        tx.commit();
        session.close();
    }

    @Override
    public void loadArticle(Article article) throws DAOException {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(article);
        session.flush();
        tx.commit();
        session.close();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
