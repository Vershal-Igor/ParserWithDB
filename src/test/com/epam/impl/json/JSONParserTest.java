package com.epam.impl.json;

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

public class JSONParserTest {
    private static final Logger logger = Logger.getLogger(JSONParserTest.class);

    private Parser JSONparser;


    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        ParserMaker JSONmaker = getParserByName(ParserType.JSON);
        JSONparser = JSONmaker.createParser();
    }


    @Test
    public void shouldParseJSON() throws Exception {
        List<Article> expected;
        List<Article> actual;

        expected = JSONparser.loadArticlesFromDirectory(Loader.getDirectory());
        actual = JSONparser.loadArticlesFromDirectory(Loader.getTestDirectory());
        logger.info(expected);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowParserException() throws ParserException {
        thrown.expect(ParserException.class);
        JSONparser.loadArticlesFromDirectory(Loader.getFailDirectory());
    }

}