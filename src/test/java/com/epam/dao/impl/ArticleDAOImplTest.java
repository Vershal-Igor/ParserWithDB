package com.epam.dao.impl;

import com.epam.parser.Loader;
import com.epam.parser.Parser;
import com.epam.parser.ParserMaker;
import com.epam.parser.ParserType;
import org.apache.log4j.Logger;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static com.epam.parser.ParserMaker.getParserByName;


public class ArticleDAOImplTest{
    private static final Logger logger = Logger.getLogger(ArticleDAOImplTest.class);

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-test-config.xml");
    ArticleDAOImpl articleDAO = (ArticleDAOImpl) applicationContext.getBean("articleDAOTest");


    @Test
    public void findAll() throws Exception {
        articleDAO.findAll();
        System.out.println(articleDAO.findAll());
    }

    @Test
    public void findByTitle() throws Exception {
    }

    @Test
    public void delete() throws Exception {
    }

    @Test
    public void deleteAll() throws Exception {
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void loadArticles() throws Exception {
    }

    @Test
    public void loadArticle() throws Exception {
        ParserMaker XMLmaker = getParserByName(ParserType.XML);
        Parser XMLparser = XMLmaker.createParser();
        articleDAO.loadArticle(XMLparser.loadArticleFromFile(Loader.getXmlArticle2()));
    }

    @Test
    public void setSessionFactory() throws Exception {
    }

}