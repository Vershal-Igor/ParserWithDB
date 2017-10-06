package com.epam;

import com.epam.exception.ParserException;
import org.apache.log4j.Logger;
import org.hamcrest.Matcher;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class LoaderTest {
    private static final Logger logger = Logger.getLogger(LoaderTest.class);

    private static final String JSON_TYPE = "json";
    private static final String TXT_TYPE = "txt";
    private static final String XML_TYPE = "xml";

    private static Object[] jsonArticlesFiles() {
        return new Object[]{
                Loader.getJsonArticle1(),
                Loader.getJsonArticle4(),
                Loader.getJsonArticle6()
        };
    }

    private static Object[] txtArticlesFiles() {
        return new Object[]{
                Loader.getTxtArticle7(),
                Loader.getTxtArticle8(),
                Loader.getTxtArticle9()
        };
    }

    private static Object[] xmlArticlesFiles() {
        return new Object[]{
                Loader.getXmlArticle2(),
                Loader.getXmlArticle3(),
                Loader.getXmlArticle5()
        };
    }

    @Test
    public void shouldReturnJSONFilesFromDirectory() throws ParserException {
        String[] expected;
        Matcher<Object[]> actual;

        expected = Loader.getInstance().loadFilesFromDirectoryByType(Loader.getDirectory(), JSON_TYPE);
        actual = is(jsonArticlesFiles());

        assertThat(expected, actual);
        logger.info(actual);
    }

    @Test
    public void shouldReturnTXTFilesFromDirectory() throws ParserException {
        String[] expected;
        Matcher<Object[]> actual;

        expected = Loader.getInstance().loadFilesFromDirectoryByType(Loader.getDirectory(), TXT_TYPE);
        actual = is(txtArticlesFiles());

        assertThat(expected, actual);
        logger.info(actual);
    }

    @Test
    public void shouldReturnXMLFilesFromDirectory() throws ParserException {
        String[] expected;
        Matcher<Object[]> actual;

        expected = Loader.getInstance().loadFilesFromDirectoryByType(Loader.getDirectory(), XML_TYPE);
        actual = is(xmlArticlesFiles());

        assertThat(expected, actual);
        logger.info(actual);
    }

}