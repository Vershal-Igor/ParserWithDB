/*
package com.epam.dao.impl;

import com.epam.entity.Article;
import com.epam.parser.Loader;
import com.epam.parser.Parser;
import com.epam.parser.ParserMaker;
import com.epam.parser.ParserType;
import com.epam.service.impl.ArticleServiceImpl;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;


import java.util.ArrayList;
import java.util.List;

import static com.epam.parser.ParserMaker.getParserByName;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-test-config.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})


public class ArticleDAOImplTest {
    private static final Logger logger = Logger.getLogger(ArticleDAOImplTest.class);
    private ParserMaker XMLmaker;
    private ParserMaker JSONmaker;
    private ParserMaker TXTmaker;
    private Parser XMLparser;
    private Parser JSONparser;
    private Parser TXTparser;
    private List<Article> XMLarticles;
    private List<Article> JSONarticles;
    private List<Article> TXTarticles;

    @Autowired
    ArticleDAOImpl articleDAO;


    @Before
    public void setUp() throws Exception {
        XMLmaker = getParserByName(ParserType.XML);
        XMLparser = XMLmaker.createParser();
        XMLarticles = XMLparser.loadArticlesFromDirectory(Loader.getDirectory());

        JSONmaker = getParserByName(ParserType.JSON);
        JSONparser = JSONmaker.createParser();
        JSONarticles = JSONparser.loadArticlesFromDirectory(Loader.getDirectory());

        TXTmaker = getParserByName(ParserType.TXT);
        TXTparser = TXTmaker.createParser();
        TXTarticles = TXTparser.loadArticlesFromDirectory(Loader.getDirectory());
    }


    @DatabaseSetup("/com.epam.entity.article/article-data.xml")
    @DatabaseTearDown(value = "/databaseTearDown.xml",
            type = DatabaseOperation.CLEAN_INSERT)
    @Test
    public void testFindAll() throws Exception {
        List<Article> apartments = articleDAO.findAll();
        final int expectedSize = 9;
        assertThat(apartments.size(), is(expectedSize));
    }

    @Test
    public void testFindByTitle() throws Exception {
        Article expected;
        Article actual;

        expected = XMLparser.loadArticleFromFile(Loader.getXmlArticle3());

        actual = articleDAO.findByTitle(Loader.getTitleArticle3());

        assertThat(expected, is(actual));
    }

    @Test
    public void delete() throws Exception {
        Article expected;
        ParserMaker XMLmaker = getParserByName(ParserType.XML);
        Parser XMLparser = XMLmaker.createParser();
        Article article = XMLparser.loadArticleFromFile(Loader.getXmlArticle3());
        articleDAO.delete(article.getTitle());
        expected = articleDAO.findByTitle(article.getTitle());

        assertNull(expected);
    }

    @Test
    public void deleteAll() throws Exception {
        articleDAO.deleteAll(XMLarticles);
        articleDAO.deleteAll(JSONarticles);
        articleDAO.deleteAll(TXTarticles);

        List<Article> apartments = articleDAO.findAll();

        assertTrue(apartments.isEmpty());
    }

    @Test
    public void testUpdate() throws Exception {
        Article expected;
        Article actual;

        expected = articleDAO.findByTitle(Loader.getTitleArticle2());
        expected.setAuthor("NEW AUTHOR");
        articleDAO.update(expected);
        actual = articleDAO.findByTitle(Loader.getTitleArticle2());

        assertThat(expected.getAuthor(), is(actual.getAuthor()));
    }

    @Test
    public void testLoadArticle() throws Exception {
        Article expected = new Article();
        Article actual;

        expected.setTitle("Test Article Load");
        expected.setAuthor("Test Article Load");

        articleDAO.loadArticle(expected);
        actual = articleDAO.findByTitle(expected.getTitle());

        assertThat(expected, is(actual));
    }

    @Test
    public void loadArticles() throws Exception {
        List<Article> articles = new ArrayList<>();

        articles.add(new Article("TestArticle1", "Test1"));
        articles.add(new Article("TestArticle2", "Test2"));
        articles.add(new Article("TestArticle3", "Test3"));
        articleDAO.loadArticles(articles);
        for (Article article : articles) {
            Article inDatabase = articleDAO.findByTitle(article.getTitle());
            assertThat(article, is(inDatabase));
        }

    }

}*/


package com.epam.dao.impl;

import com.epam.entity.Article;
import com.epam.parser.Loader;
import com.epam.parser.Parser;
import com.epam.parser.ParserMaker;
import com.epam.parser.ParserType;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.*;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static com.epam.parser.ParserMaker.getParserByName;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-test-config.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})


public class ArticleDAOImplTest {
    private static final Logger logger = Logger.getLogger(ArticleDAOImplTest.class);
    private ParserMaker XMLmaker;
    private ParserMaker JSONmaker;
    private ParserMaker TXTmaker;
    private Parser XMLparser;
    private Parser JSONparser;
    private Parser TXTparser;
    private List<Article> XMLarticles;
    private List<Article> JSONarticles;
    private List<Article> TXTarticles;

    @Autowired
    ArticleDAOImpl articleDAO;


    @Before
    public void setUp() throws Exception {
        XMLmaker = getParserByName(ParserType.XML);
        XMLparser = XMLmaker.createParser();
        XMLarticles = XMLparser.loadArticlesFromDirectory(Loader.getDirectory());

        JSONmaker = getParserByName(ParserType.JSON);
        JSONparser = JSONmaker.createParser();
        JSONarticles = JSONparser.loadArticlesFromDirectory(Loader.getDirectory());

        TXTmaker = getParserByName(ParserType.TXT);
        TXTparser = TXTmaker.createParser();
        TXTarticles = TXTparser.loadArticlesFromDirectory(Loader.getDirectory());
    }


    @DatabaseSetup("/com.epam.entity.article/article-data.xml")
    @DatabaseTearDown(value = "/databaseTearDown.xml",
            type = DatabaseOperation.CLEAN_INSERT)
    @Test
    public void testFindAll() throws Exception {
        List<Article> apartments = articleDAO.findAll();
        final int expectedSize = 8;
        assertThat(apartments.size()).isEqualTo(expectedSize);
    }

    /*@DatabaseSetup("/com.epam.entity.article/article-data.xml")
    @DatabaseTearDown(value = "/databaseTearDown.xml",
            type = DatabaseOperation.CLEAN_INSERT)
    @Test
    public void testFindByTitle() throws Exception {
        Article actual;
        Article expected;

        actual = articleDAO.findByTitle(Loader.getTitleArticle2());
        expected = XMLparser.loadArticleFromFile(Loader.getXmlArticle2());

        assertThat(actual).isEqualTo(expected);
        assertThat(actual).isNotNull();
        assertThat(actual.getTitle()).isEqualTo(expected.getTitle());
        assertThat(actual.getAuthor()).isEqualTo(expected.getAuthor());
        assertThat(actual.getContents()).isEqualTo(expected.getContents());
    }*/

    @DatabaseSetup("/com.epam.entity.article/article-data.xml")
    @ExpectedDatabase("/com.epam.entity.article/expexted-delete-data.xml")
    @DatabaseTearDown(value = "/databaseTearDown.xml",
            type = DatabaseOperation.CLEAN_INSERT)
    @Test
    public void delete() throws Exception {
        Article article = XMLparser.loadArticleFromFile(Loader.getXmlArticle2());
        articleDAO.delete(article.getTitle());
    }

    @DatabaseSetup("/com.epam.entity.article/article-data.xml")
    @ExpectedDatabase("/com.epam.entity.article/expexted-deleteAll-data.xml")
    @DatabaseTearDown(value = "/databaseTearDown.xml",
            type = DatabaseOperation.CLEAN_INSERT)
    @Test
    public void deleteAll() throws Exception {
        articleDAO.deleteAll(JSONarticles);
    }

    @DatabaseSetup("/com.epam.entity.article/article-data.xml")
    @ExpectedDatabase("/com.epam.entity.article/expexted-article-data.xml")
    @DatabaseTearDown(value = "/databaseTearDown.xml",
            type = DatabaseOperation.CLEAN_INSERT)
    @Test
    public void testUpdate() throws Exception {
        Article expected;

        expected = TXTparser.loadArticleFromFile(Loader.getTxtArticle9());
        expected.setAuthor("TEST AUTHOR");
        expected.setContents("hey");

        articleDAO.update(expected);
    }


    @Test
    public void loadArticle() throws Exception {

    }

 /*   @DatabaseSetup("/com.epam.entity.article/load-article-data.xml")
    @ExpectedDatabase("/com.epam.entity.article/article-data.xml")
    @DatabaseTearDown(value = "/databaseTearDown.xml",
            type = DatabaseOperation.CLEAN_INSERT)
    @Test
    public void loadArticles() throws Exception {
        articleDAO.loadArticles(XMLarticles);
    }*/
}