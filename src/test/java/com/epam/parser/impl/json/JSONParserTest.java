package com.epam.parser.impl.json;

import com.epam.entity.ArticleCreator;
import com.epam.parser.Loader;
import com.epam.parser.Parser;
import com.epam.parser.ParserMaker;
import com.epam.parser.ParserType;
import com.epam.entity.Article;
import com.epam.parser.exception.ParserException;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.epam.parser.ParserMaker.getParserByName;
import static org.junit.Assert.*;

public class JSONParserTest {
    private static final Logger logger = Logger.getLogger(JSONParserTest.class);

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

    private ParserMaker JSONmaker;
    private Parser JSONparser;


    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        JSONmaker = getParserByName(ParserType.JSON);
        JSONparser = JSONmaker.createParser();
    }


    @Test
    public void shouldParseAllJSONFromDirectory() throws Exception {
        List<Article> expected = new ArrayList<>();
        List<Article> actual;

        expected.add(JSON_ARTICLE_1);
        expected.add(JSON_ARTICLE_2);
        expected.add(JSON_ARTICLE_3);

        actual = JSONparser.loadArticlesFromDirectory(Loader.getDirectory());
        logger.info(actual);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnCorrectAttributesForJSONArticleWithAuthor() throws Exception {
        Article actual;

        actual = JSONparser.loadArticleFromFile(Loader.getJsonArticle1());
        logger.info(actual);

        assertEquals(JSON_ARTICLE_1, actual);
    }

    @Test
    public void shouldReturnCorrectAttributesForJSONArticleWithoutAuthor() throws Exception {
        Article actual;

        actual = JSONparser.loadArticleFromFile(Loader.getJsonArticle3());
        logger.info(actual);

        assertEquals(JSON_ARTICLE_3, actual);
    }

    @Test
    public void shouldThrowParserException() throws ParserException {
        thrown.expect(ParserException.class);
        JSONparser.loadArticlesFromDirectory(Loader.getFailDirectory());
    }

}
