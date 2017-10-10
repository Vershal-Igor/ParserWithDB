package com.epam.dao.impl;

import com.epam.entity.Article;
import com.epam.parser.Loader;
import com.epam.parser.Parser;
import com.epam.parser.ParserMaker;
import com.epam.parser.ParserType;
import com.epam.service.impl.ArticleServiceImpl;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.util.List;

import static com.epam.parser.ParserMaker.getParserByName;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")


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
    ArticleServiceImpl articleService;
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

        articleService.deleteAll();
        articleService.loadArticles(XMLarticles);
        articleService.loadArticles(JSONarticles);
        articleService.loadArticles(TXTarticles);
    }


    @Test
    public void testFindAll() throws Exception {
        List<Article> apartments = articleDAO.findAll();
        final int expectedSize = 9;
        assertThat(apartments.size(), is(expectedSize));
        assertNotNull(apartments);
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
    }

}