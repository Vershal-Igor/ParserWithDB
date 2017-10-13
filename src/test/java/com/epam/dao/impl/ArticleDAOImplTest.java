
package com.epam.dao.impl;

import com.epam.entity.Article;
import com.epam.entity.ArticleCreator;
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

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-test-config.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})


public class ArticleDAOImplTest {

    private static final ResourceBundle RB = ResourceBundle.getBundle("properties/common");

    private static final Article JSON_ARTICLE_1 = new ArticleCreator()
            .setTitle(RB.getString("JSON.TITLE.1"))
            .setAuthor(RB.getString("JSON.AUTHOR.1"))
            .setContents(RB.getString("JSON.CONTENTS.1"))
            .create();

    private static final Article JSON_ARTICLE_2 = new ArticleCreator()
            .setTitle(RB.getString("JSON.TITLE.2"))
            .setAuthor(RB.getString("JSON.AUTHOR.2"))
            .setContents(RB.getString("JSON.CONTENTS.2"))
            .create();

    private static final Article JSON_ARTICLE_3 = new ArticleCreator()
            .setTitle(RB.getString("JSON.TITLE.3"))
            .setAuthor(RB.getString("JSON.AUTHOR.3"))
            .setContents(RB.getString("JSON.CONTENTS.3"))
            .create();

    private static final Article TXT_ARTICLE_1 = new ArticleCreator()
            .setTitle(RB.getString("TXT.TITLE.1"))
            .setAuthor(RB.getString("TXT.AUTHOR.1"))
            .setContents(RB.getString("TXT.CONTENTS.1"))
            .create();

    private static final Article TXT_ARTICLE_2 = new ArticleCreator()
            .setTitle(RB.getString("TXT.TITLE.2"))
            .setAuthor(RB.getString("TXT.AUTHOR.2"))
            .setContents(RB.getString("TXT.CONTENTS.2"))
            .create();

    private static final Article TXT_ARTICLE_3 = new ArticleCreator()
            .setTitle(RB.getString("TXT.TITLE.3"))
            .setAuthor(RB.getString("TXT.AUTHOR.3"))
            .setContents(RB.getString("TXT.CONTENTS.3"))
            .create();

    private static final Article XML_ARTICLE_1 = new ArticleCreator()
            .setTitle(RB.getString("XML.TITLE.1"))
            .setAuthor(RB.getString("XML.AUTHOR.1"))
            .setContents(RB.getString("XML.CONTENTS.1"))
            .create();

    private static final Article XML_ARTICLE_2 = new ArticleCreator()
            .setTitle(RB.getString("XML.TITLE.2"))
            .setAuthor(RB.getString("XML.AUTHOR.2"))
            .setContents(RB.getString("XML.CONTENTS.2"))
            .create();

    private static final Article XML_ARTICLE_3 = new ArticleCreator()
            .setTitle(RB.getString("XML.TITLE.3"))
            .setAuthor(RB.getString("XML.AUTHOR.3"))
            .setContents(RB.getString("XML.CONTENTS.3"))
            .create();

    private List<Article> XMLarticles = new ArrayList<>();
    private List<Article> JSONarticles = new ArrayList<>();
    private List<Article> TXTarticles = new ArrayList<>();

    @Autowired
    ArticleDAOImpl articleDAO;

    @Before
    public void setUp() throws Exception {

        XMLarticles.add(XML_ARTICLE_1);
        XMLarticles.add(XML_ARTICLE_2);
        XMLarticles.add(XML_ARTICLE_3);

        JSONarticles.add(JSON_ARTICLE_1);
        JSONarticles.add(JSON_ARTICLE_2);
        JSONarticles.add(JSON_ARTICLE_3);

        TXTarticles.add(TXT_ARTICLE_1);
        TXTarticles.add(TXT_ARTICLE_2);
        TXTarticles.add(TXT_ARTICLE_3);
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
        Article expected;
        Article actual;

        expected = JSON_ARTICLE_1;
        String title = JSON_ARTICLE_1.getTitle();

        actual = articleDAO.findByTitle(title);

        assertThat(expected).isEqualTo(actual);
        assertThat(expected).isNotNull();
        assertThat(expected.getTitle()).isEqualTo(actual.getTitle());
        assertThat(expected.getAuthor()).isEqualTo(actual.getAuthor());
        assertThat(expected.getContents()).isEqualTo(actual.getContents());
    }

    @DatabaseSetup("/com.epam.entity.article/article-data.xml")
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED,
            value = "/com.epam.entity.article/delete-article-data.xml")
    @DatabaseTearDown(value = "/databaseTearDown.xml",
            type = DatabaseOperation.CLEAN_INSERT)
    @Test
    public void shouldDeleteByTitle() throws Exception {
        String title = XML_ARTICLE_1.getTitle();
        articleDAO.delete(title);
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
        Article actual;

        actual = TXT_ARTICLE_3;
        actual.setAuthor("TEST AUTHOR");
        actual.setContents("hey");

        articleDAO.update(actual);
    }

    @DatabaseSetup("/com.epam.entity.article/load-article-data.xml")
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED,
            value = "/com.epam.entity.article/article-data.xml")
    @DatabaseTearDown(value = "/databaseTearDown.xml",
            type = DatabaseOperation.CLEAN_INSERT)
    @Test
    public void shouldSaveArticle() throws Exception {
        Article article;

        article = JSON_ARTICLE_2;

        articleDAO.save(article);
    }

    @DatabaseSetup("/com.epam.entity.article/load-articles-data.xml")
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED,
            value = "/com.epam.entity.article/article-data.xml")
    @DatabaseTearDown(value = "/databaseTearDown.xml",
            type = DatabaseOperation.CLEAN_INSERT)
    @Test
    public void shouldSaveArticles() throws Exception {
        articleDAO.saveArticles(JSONarticles);
    }
}
