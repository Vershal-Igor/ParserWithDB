package com.epam.parser.impl.txt;

import com.epam.entity.ArticleCreator;
import com.epam.parser.Loader;
import com.epam.parser.Parser;
import com.epam.parser.ParserMaker;
import com.epam.parser.ParserType;
import com.epam.entity.Article;
import com.epam.parser.exception.ParserException;
import com.epam.parser.reader.Reader;
import com.epam.parser.reader.impl.TXTReader;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.epam.parser.ParserMaker.getParserByName;
import static org.junit.Assert.*;

public class TXTParserTest {
    private static final Logger logger = Logger.getLogger(TXTParserTest.class);

    private static final ResourceBundle RB = ResourceBundle.getBundle("properties/common");

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

    private Parser TXTparser;
    private TXTParser txtParser;
    private Reader reader;


    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        ParserMaker TXTmaker = getParserByName(ParserType.TXT);
        TXTparser = TXTmaker.createParser();
        txtParser = new TXTParser();
        reader = new TXTReader();
    }


    @Test
    public void shouldParseTXT() throws Exception {
        List<Article> actual;
        List<Article> expected = new ArrayList<>();

        actual = TXTparser.loadArticlesFromDirectory(Loader.getDirectory());

        expected.add(TXT_ARTICLE_1);
        expected.add(TXT_ARTICLE_2);
        expected.add(TXT_ARTICLE_3);


        assertEquals(expected, actual);
    }

    @Test
    public void shouldParseTXT1() throws Exception {
        Article expected;
        Article actual;

        expected = TXTparser.loadArticleFromFile(Loader.getTxtArticle1());

        Article actualArticle = reader.read(new File(Loader.getTxtArticle1()));
        assertEquals(expected.getTitle(), actualArticle.getTitle());
    }

    @Test
    public void shouldReturnCorrectAuthorNameForStandardArticle() throws Exception {
        Article article;

        article = txtParser.loadArticleFromFile(Loader.getTxtArticle1());
        String expectedAuthorName = article.getAuthor();

        String actualAuthorName = TXT_ARTICLE_1.getAuthor();

        assertEquals(expectedAuthorName, actualAuthorName);

        logger.info(expectedAuthorName);
    }

    @Test
    public void shouldReturnCorrectAuthorNameForArticleWithoutAuthor() throws Exception {
        Article article;

        article = txtParser.loadArticleFromFile(Loader.getTxtArticle3());
        String expectedAuthorName = article.getAuthor();

        String actualAuthorName = TXT_ARTICLE_3.getAuthor();

        assertEquals(expectedAuthorName, actualAuthorName);

        logger.info(expectedAuthorName);
    }

    @Test
    public void shouldThrowParserException() throws ParserException {
        thrown.expect(ParserException.class);
        TXTparser.loadArticlesFromDirectory(Loader.getFailDirectory());
    }


}
