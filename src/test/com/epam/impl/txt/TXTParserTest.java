package com.epam.impl.txt;

import com.epam.Loader;
import com.epam.Parser;
import com.epam.ParserMaker;
import com.epam.ParserType;
import com.epam.entity.Article;
import com.epam.exception.ParserException;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static com.epam.ParserMaker.getParserByName;
import static org.junit.Assert.*;

public class TXTParserTest {
    private static final Logger logger = Logger.getLogger(TXTParserTest.class);

    private Parser TXTparser;
    private TXTParser txtParser;


    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        ParserMaker TXTmaker = getParserByName(ParserType.TXT);
        TXTparser = TXTmaker.createParser();
        txtParser = new TXTParser();
    }


    @Test
    public void shouldParseTXT() throws Exception {
        List<Article> expected;
        List<Article> actual;

        expected = TXTparser.loadArticlesFromDirectory(Loader.getDirectory());
        actual = TXTparser.loadArticlesFromDirectory(Loader.getTestDirectory());

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnCorrectAuthorNameForArticle7() throws Exception {
        String expectedAuthorName;
        String actualAuthorName;

        expectedAuthorName = txtParser.pullAuthorName(Loader.getTxtArticle7());
        actualAuthorName = Loader.getAuthorArticle7();

        assertEquals(expectedAuthorName, actualAuthorName);

        logger.info(expectedAuthorName);
    }

    @Test
    public void shouldReturnCorrectAuthorNameForArticle8() throws Exception {
        String expectedAuthorName;
        String actualAuthorName;

        expectedAuthorName = txtParser.pullAuthorName(Loader.getTxtArticle8());
        actualAuthorName = Loader.getAuthorArticle8();

        assertEquals(expectedAuthorName, actualAuthorName);

        logger.info(expectedAuthorName);
    }

    @Test
    public void shouldReturnCorrectAuthorNameForArticle9() throws Exception {
        String expectedAuthorName;
        String actualAuthorName;

        expectedAuthorName = txtParser.pullAuthorName(Loader.getTxtArticle9());
        actualAuthorName = Loader.getDefaultElemenent();

        assertEquals(expectedAuthorName, actualAuthorName);

        logger.info(expectedAuthorName);
    }

    @Test
    public void shouldThrowParserException() throws ParserException {
        thrown.expect(ParserException.class);
        TXTparser.loadArticlesFromDirectory(Loader.getFailDirectory());
    }


}