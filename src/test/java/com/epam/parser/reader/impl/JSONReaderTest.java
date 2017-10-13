package com.epam.parser.reader.impl;

import com.epam.entity.Article;
import com.epam.entity.ArticleCreator;
import com.epam.parser.Loader;
import com.epam.parser.reader.Reader;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ResourceBundle;

import static org.junit.Assert.*;


public class JSONReaderTest {
    private static final Logger logger = Logger.getLogger(JSONReaderTest.class);
    private static final ResourceBundle RB = ResourceBundle.getBundle("properties/common");

    private static final Article JSON_ARTICLE_1 = new ArticleCreator()
            .setTitle(RB.getString("JSON.TITLE.1"))
            .setAuthor(RB.getString("JSON.AUTHOR.1"))
            .setContents(RB.getString("JSON.CONTENTS.1"))
            .create();

    Reader jsonReader;
    File file;

    @Before
    public void setUp() throws Exception {
        jsonReader = new JSONReader();
        file = new File(Loader.getJsonArticle1());
    }

    @Test
    public void shouldReadJSONArticleFromFile() throws Exception {
        Article expected;
        Article actual;
        expected = JSON_ARTICLE_1;
        actual = jsonReader.read(file);

        logger.info(expected);
        assertEquals(expected, actual);
    }

}