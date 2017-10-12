
package com.epam.dao.impl;

import com.epam.entity.Article;
import com.epam.parser.Loader;
import com.epam.parser.Parser;
import com.epam.parser.ParserMaker;
import com.epam.parser.ParserType;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.*;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
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
    public void shouldFindAll() throws Exception {
        List<Article> apartments = articleDAO.findAll();
        final int expectedSize = 8;
        assertThat(apartments.size()).isEqualTo(expectedSize);
    }

    @DatabaseSetup("/com.epam.entity.article/article-data.xml")
    @DatabaseTearDown(value = "/databaseTearDown.xml",
            type = DatabaseOperation.CLEAN_INSERT)
    @Test
    public void shouldFindByTitle() throws Exception {
        Article actual;
        Article expected;

        actual = articleDAO.findByTitle(Loader.getTitleArticle1());
        expected = JSONparser.loadArticleFromFile(Loader.getJsonArticle1());

        assertThat(actual).isEqualTo(expected);
        assertThat(actual).isNotNull();
        assertThat(actual.getTitle()).isEqualTo(expected.getTitle());
        assertThat(actual.getAuthor()).isEqualTo(expected.getAuthor());
        assertThat(actual.getContents()).isEqualTo(expected.getContents());
    }

    @DatabaseSetup("/com.epam.entity.article/article-data.xml")
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED,
            value = "/com.epam.entity.article/delete-article-data.xml")
    @DatabaseTearDown(value = "/databaseTearDown.xml",
            type = DatabaseOperation.CLEAN_INSERT)
    @Test
    public void shouldDeleteByTitle() throws Exception {
        Article article = XMLparser.loadArticleFromFile(Loader.getXmlArticle2());
        articleDAO.delete(article.getTitle());
    }

    @DatabaseSetup("/com.epam.entity.article/article-data.xml")
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED,
            value = "/com.epam.entity.article/deleteAllFromSet-article-data.xml")
    @DatabaseTearDown(value = "/databaseTearDown.xml",
            type = DatabaseOperation.CLEAN_INSERT)
    @Test
    public void shouldDeleteAllFromList() throws Exception {
        articleDAO.deleteAllFromList(JSONarticles);
        articleDAO.deleteAllFromList(TXTarticles);
    }

    @DatabaseSetup("/com.epam.entity.article/article-data.xml")
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED,
            value = "/com.epam.entity.article/deleteAll-article-data.xml")
    @DatabaseTearDown(value = "/databaseTearDown.xml",
            type = DatabaseOperation.CLEAN_INSERT)
    @Test
    public void shouldDeleteAll() throws Exception {
        articleDAO.deleteAll();
    }

    @DatabaseSetup("/com.epam.entity.article/article-data.xml")
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED,
            value = "/com.epam.entity.article/update-article-data.xml")
    @DatabaseTearDown(value = "/databaseTearDown.xml",
            type = DatabaseOperation.CLEAN_INSERT)
    @Test
    public void shouldUpdateArticle() throws Exception {
        Article expected;

        expected = TXTparser.loadArticleFromFile(Loader.getTxtArticle9());
        expected.setAuthor("TEST AUTHOR");
        expected.setContents("hey");

        articleDAO.update(expected);
    }


    @DatabaseSetup("/com.epam.entity.article/load-article-data.xml")
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED,
            value = "/com.epam.entity.article/article-data.xml")
    @DatabaseTearDown(value = "/databaseTearDown.xml",
            type = DatabaseOperation.CLEAN_INSERT)
    @Test
    public void shouldLoadArticle() throws Exception {
        Article article;

        article = JSONparser.loadArticleFromFile(Loader.getJsonArticle4());

        articleDAO.loadArticle(article);
    }

    @DatabaseSetup("/com.epam.entity.article/load-articles-data.xml")
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED,
            value = "/com.epam.entity.article/article-data.xml")
    @DatabaseTearDown(value = "/databaseTearDown.xml",
            type = DatabaseOperation.CLEAN_INSERT)
    @Test
    public void shouldLoadArticles() throws Exception {
        articleDAO.loadArticles(JSONarticles);
    }
}