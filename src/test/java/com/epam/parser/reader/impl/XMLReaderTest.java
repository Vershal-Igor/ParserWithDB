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


public class XMLReaderTest {
    private static final Logger logger = Logger.getLogger(XMLReaderTest.class);
    private static final ResourceBundle RB = ResourceBundle.getBundle("properties/common");

    private static final Article XML_ARTICLE_3 = new ArticleCreator()
            .setTitle(RB.getString("XML.TITLE.3"))
            .setAuthor(RB.getString("XML.AUTHOR.3"))
            .setContents(RB.getString("XML.CONTENTS.3"))
            .create();

    Reader xmlReader;
    File file;

    @Before
    public void setUp() throws Exception {
        xmlReader = new XMLReader();
        file = new File(Loader.getXmlArticle3());
    }

    @Test
    public void shouldReadXMLArticleFromFile() throws Exception {
        Article expected;
        Article actual;
        expected = XML_ARTICLE_3;
        actual = xmlReader.read(file);

        logger.info(expected);
        assertEquals(expected, actual);
    }

}