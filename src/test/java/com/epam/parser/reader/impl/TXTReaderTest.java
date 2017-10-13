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


public class TXTReaderTest {
    private static final Logger logger = Logger.getLogger(TXTReaderTest.class);
    private static final ResourceBundle RB = ResourceBundle.getBundle("properties/common");

    private static final Article TXT_ARTICLE_2 = new ArticleCreator()
            .setTitle(RB.getString("TXT.TITLE.2"))
            .setAuthor(RB.getString("TXT.AUTHOR.2"))
            .setContents(RB.getString("TXT.CONTENTS.2"))
            .create();

    Reader txtReader;
    File file;

    @Before
    public void setUp() throws Exception {
        txtReader = new TXTReader();
        file = new File(Loader.getTxtArticle2());
    }

    @Test
    public void shouldReadTXTArticleFromFile() throws Exception {
        Article expected;
        Article actual;
        expected = TXT_ARTICLE_2;
        actual = txtReader.read(file);

        logger.info(expected);
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getAuthor(), actual.getAuthor());
    }

    @Test
    public void shouldPullAuthorNameFromTXTFile() throws Exception {
        TXTReader txtReader = new TXTReader();
        String expectedAuthorName;
        String actualAuthorName;

        expectedAuthorName = txtReader.pullAuthorName(file);
        actualAuthorName = TXT_ARTICLE_2.getAuthor();

        assertEquals(expectedAuthorName, actualAuthorName);

        logger.info(expectedAuthorName);
    }

}