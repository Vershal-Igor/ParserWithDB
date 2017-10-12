/*
package com.epam.service.impl;


import com.epam.dao.impl.ArticleDAOImpl;
import com.epam.entity.Article;
import com.epam.parser.Loader;
import com.epam.parser.Parser;
import com.epam.parser.ParserMaker;
import com.epam.parser.ParserType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static com.epam.parser.ParserMaker.getParserByName;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ArticleServiceImplTest {

    @InjectMocks
    private ArticleServiceImpl articleService;
    @Mock
    private ArticleDAOImpl articleDAO;

    private ParserMaker XMLmaker;
    private ParserMaker JSONmaker;
    private ParserMaker TXTmaker;
    private Parser XMLparser;
    private Parser JSONparser;
    private Parser TXTparser;
    private List<Article> XMLarticles;
    private List<Article> JSONarticles;
    private List<Article> TXTarticles;

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

        initMocks(this);
    }

    @Test
    public void shouldFindAll() throws Exception {
        final List<Article> expectedArticles = Collections.EMPTY_LIST;

        when(articleDAO.findAll()).thenReturn(expectedArticles);
        List<Article> actualArticles = articleService.findAll();

        assertNotNull(actualArticles);
        assertEquals(expectedArticles, actualArticles);
        verify(articleDAO).findAll();
    }

    @Test
    public void shouldFindByTitle() throws Exception {
        Article expectedArticle = JSONparser.loadArticleFromFile(Loader.getJsonArticle1());

        when(articleDAO.findByTitle(Loader.getTitleArticle1())).thenReturn(expectedArticle);
        Article actualArticle = articleService.findByTitle(Loader.getTitleArticle1());

        assertNotNull(actualArticle);
        assertEquals(expectedArticle, actualArticle);
        verify(articleDAO).findByTitle(Loader.getTitleArticle1());
    }

    @Test
    public void shouldDeleteByTitle() throws Exception {
        articleDAO.delete(Loader.getTitleArticle9());
        articleService.delete(Loader.getTitleArticle9());
        verify(articleDAO, times(2)).delete(Loader.getTitleArticle9());
    }

    @Test
    public void shouldDeleteAllFromList() throws Exception {
        articleDAO.deleteAllFromList(XMLarticles);

        articleService.deleteAllFromList(XMLarticles);

        verify(articleDAO, times(2)).deleteAllFromList(XMLarticles);
    }

    @Test
    public void shouldDeleteAll() throws Exception {
        articleDAO.deleteAll();

        articleService.deleteAll();

        verify(articleDAO, times(2)).deleteAll();
    }

    @Test
    public void shouldUpdate() throws Exception {
    }

    @Test
    public void shouldLoadArticles() throws Exception {
        Article expectrdArticle = JSONparser.loadArticleFromFile(Loader.getJsonArticle1());

        articleDAO.save(expectrdArticle);
        articleDAO.saveArticles(JSONarticles);

        articleService.loadArticles(JSONarticles);

        //verify(articleDAO,times(2)).save(expectrdArticle);
        verify(articleDAO).saveArticles(JSONarticles);
    }

    @Test
    public void shouldLoadArticle() throws Exception {
        Article expectrdArticle = TXTparser.loadArticleFromFile(Loader.getTxtArticle9());
        articleDAO.save(expectrdArticle);

        articleService.loadArticle(expectrdArticle);

        verify(articleDAO, times(2)).save(expectrdArticle);
    }

}
*/
