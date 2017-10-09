package com.epam.dao.impl;

import com.epam.entity.Article;
import com.epam.parser.Loader;
import com.epam.parser.Parser;
import com.epam.parser.ParserMaker;
import com.epam.parser.ParserType;
import config.DBUnitConfig;
import org.apache.log4j.Logger;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static com.epam.parser.ParserMaker.getParserByName;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class ArticleDAOImplTest extends DBUnitConfig {
    private static final Logger logger = Logger.getLogger(ArticleDAOImplTest.class);

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-test-config.xml");
    ArticleDAOImpl articleDAO = (ArticleDAOImpl) applicationContext.getBean("articleDAOTest");

    public ArticleDAOImplTest(String name) {
        super(name);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        beforeData = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader().getResourceAsStream("com.epam.entity.article/article-data.xml"));
        tester.setDataSet(beforeData);
        tester.onSetup();
    }

    @Test
    public void testFindAll() throws Exception {
        List<Article> apartments = articleDAO.findAll();

        IDataSet expectedData = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader().getResourceAsStream("com.epam.entity.article/article-data.xml"));
        logger.debug(expectedData.getTable("TESTARTICLE").getRowCount());
        Assert.assertEquals(expectedData.getTable("TESTARTICLE").getRowCount(), apartments.size());
        assertThat(expectedData.getTable("TESTARTICLE").getRowCount(), is(apartments.size()));
    }

    @Test
    public void testFindByTitle() throws Exception {
        Article actual = articleDAO.findByTitle(Loader.getTitleArticle2());

        ParserMaker XMLmaker = getParserByName(ParserType.XML);
        Parser XMLparser = XMLmaker.createParser();
        Article expected = XMLparser.loadArticleFromFile(Loader.getXmlArticle2());
        assertThat(actual, is(expected));
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

   /* @Test
    public void loadArticle() throws Exception {
        ParserMaker XMLmaker = getParserByName(ParserType.XML);
        Parser XMLparser = XMLmaker.createParser();
        articleDAO.loadArticle(XMLparser.loadArticleFromFile(Loader.getXmlArticle2()));
    }*/




    /*@Test
    public void testFindByTitle() throws Exception {
        Article expected;
        Article actual;

        expected = XMLparser.loadArticleFromFile(Loader.getXmlArticle3());

        actual = articleDAO.findByTitle(Loader.getTitleArticle3());

        assertThat(expected, is(actual));
    }

    @Test
    public void testDelete() throws Exception {
        Article article = new Article();
        Article expected;
        article.setTitle("TEST");
        article.setAuthor("TEST");
        articleDAO.loadArticle(article);
        articleDAO.delete(article.getTitle());
        expected = articleDAO.findByTitle(article.getTitle());

        assertNull(expected);
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

        expected.setTitle("AddTest");
        expected.setAuthor("AddTest");

        articleDAO.loadArticle(expected);
        actual = articleDAO.findByTitle(expected.getTitle());

        assertThat(expected, is(actual));
    }*/

}