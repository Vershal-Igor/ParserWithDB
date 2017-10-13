package com.epam.service.impl;


import com.epam.dao.impl.ArticleDAOImpl;
import com.epam.entity.Article;
import com.epam.entity.ArticleCreator;
import com.epam.parser.Loader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

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
        Article expectedArticle = JSON_ARTICLE_1;

        when(articleDAO.findByTitle(Loader.getTitleArticle1())).thenReturn(expectedArticle);
        Article actualArticle = articleService.findByTitle(JSON_ARTICLE_1.getTitle());

        assertNotNull(actualArticle);
        assertEquals(expectedArticle, actualArticle);
        verify(articleDAO).findByTitle(JSON_ARTICLE_1.getTitle());
    }

    @Test
    public void shouldDeleteByTitle() throws Exception {
        String title = TXT_ARTICLE_3.getTitle();
        articleDAO.delete(title);
        articleService.delete(title);
        verify(articleDAO, times(2)).delete(title);
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
        Article expectedArticle = TXT_ARTICLE_3;

        expectedArticle.setAuthor("NEW AUTHOR");

        articleDAO.update(expectedArticle);
        articleService.update(expectedArticle);

        assertEquals(expectedArticle.getAuthor(),"NEW AUTHOR");
        verify(articleDAO, times(2)).update(expectedArticle);
    }

    @Test
    public void shouldSaveArticles() throws Exception {
        Article expectedArticle = JSON_ARTICLE_1;

        articleDAO.save(expectedArticle);
        articleDAO.saveArticles(JSONarticles);

        articleService.saveArticles(JSONarticles);

        assertNotNull(expectedArticle);
        verify(articleDAO).saveArticles(JSONarticles);
    }

    @Test
    public void shouldSaveArticle() throws Exception {
        Article expectedArticle = TXT_ARTICLE_3;
        articleDAO.save(expectedArticle);

        articleService.save(expectedArticle);

        assertNotNull(expectedArticle);
        verify(articleDAO, times(2)).save(expectedArticle);
    }

}
