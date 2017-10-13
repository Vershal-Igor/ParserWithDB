package com.epam.parser.impl.xml;

import com.epam.entity.ArticleCreator;
import com.epam.parser.Loader;
import com.epam.parser.Parser;
import com.epam.parser.ParserMaker;
import com.epam.parser.ParserType;
import com.epam.entity.Article;
import com.epam.parser.exception.ParserException;
import com.epam.parser.reader.Reader;
import com.epam.parser.reader.impl.XMLReader;
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

public class XMLParserTest {
    private static final Logger logger = Logger.getLogger(XMLParserTest.class);

    private static final ResourceBundle RB = ResourceBundle.getBundle("properties/common");

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


    private Parser XMLparser;
    private Reader reader;


    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        ParserMaker XMLmaker = getParserByName(ParserType.XML);
        XMLparser = XMLmaker.createParser();
        reader = new XMLReader();
    }

    @Test
    public void shouldParseAllXMLFromDirectory() throws Exception {

        List<Article> expected = new ArrayList<>();
        List<Article> actual = new ArrayList<>();

        Article eXML1 = XMLparser.loadArticleFromFile(Loader.getXmlArticle1());
        Article eXML2 = XMLparser.loadArticleFromFile(Loader.getXmlArticle2());
        Article eXML3 = XMLparser.loadArticleFromFile(Loader.getXmlArticle3());
        expected.add(eXML1);
        expected.add(eXML2);
        expected.add(eXML3);

        Article txt1 = reader.read(new File(Loader.getXmlArticle1()));
        Article txt2 = reader.read(new File(Loader.getXmlArticle2()));
        Article txt3 = reader.read(new File(Loader.getXmlArticle3()));
        actual.add(txt1);
        actual.add(txt2);
        actual.add(txt3);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnCorrectAttributesForXMLArticleWithAuthor() throws ParserException {
        Article expected;
        Article actual;

        expected = XMLparser.loadArticleFromFile(Loader.getXmlArticle1());
        actual = XML_ARTICLE_1;

        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getAuthor(), actual.getAuthor());

        logger.info(expected);
    }

    @Test
    public void shouldReturnCorrectAttributesForXMLArticleWithoutAuthor() throws ParserException {
        Article expected;
        Article actual;

        expected = XMLparser.loadArticleFromFile(Loader.getXmlArticle2());
        actual = XML_ARTICLE_2;

        assertEquals(expected, actual);

        logger.info(expected);
    }

    @Test
    public void shouldReturnCorrectAttributesForXMLArticleWithoutTitle() throws ParserException {
        Article expected;
        Article actual;

        expected = XMLparser.loadArticleFromFile(Loader.getXmlArticle3());
        actual = XML_ARTICLE_3;

        assertEquals(expected, actual);

        logger.info(expected);
    }

    @Test
    public void shouldThrowParserException() throws ParserException {
        thrown.expect(ParserException.class);
        XMLparser.loadArticlesFromDirectory(Loader.getFailDirectory());
    }

}
