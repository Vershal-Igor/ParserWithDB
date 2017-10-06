package com.epam.impl.xml;

import com.epam.Loader;
import com.epam.Parser;
import com.epam.ParserMaker;
import com.epam.ParserType;
import com.epam.entity.Article;
import com.epam.exception.ParserException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.log4j.Logger;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


import java.io.*;
import java.util.List;

import static com.epam.ParserMaker.getParserByName;
import static com.epam.impl.xml.XMLParser.inputStreamToString;
import static com.epam.impl.xml.XMLParser.returnArticleWithCorrectValues;
import static org.junit.Assert.*;

public class XMLParserTest {
    private static final Logger logger = Logger.getLogger(XMLParserTest.class);


    private Parser XMLparser;
    private XmlMapper xmlMapper;


    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        ParserMaker XMLmaker = getParserByName(ParserType.XML);
        XMLparser = XMLmaker.createParser();

        xmlMapper = new XmlMapper();
    }


    @Test
    public void shouldParseXML() throws Exception {
        List<Article> expected;
        List<Article> actual;

        expected = XMLparser.loadArticlesFromDirectory(Loader.getDirectory());
        actual = XMLparser.loadArticlesFromDirectory(Loader.getTestDirectory());

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnCorrectParametersForArticle2() throws IOException {
        String expectedTitle;
        String expectedAuthor;

        String actualTitle;
        String actualAuthor;

        File xmlFile2 = new File(Loader.getXmlArticle2());

        String xml2 = inputStreamToString(new FileInputStream(xmlFile2));
        Article article2 = xmlMapper.readValue(xml2, Article.class);
        logger.info(returnArticleWithCorrectValues(article2));

        expectedTitle = article2.getTitle();
        actualTitle = Loader.getTitleArticle2();

        expectedAuthor = article2.getAuthor();
        actualAuthor = Loader.getAuthorArticle2();

        assertEquals(expectedTitle, actualTitle);
        assertEquals(expectedAuthor, actualAuthor);
    }

    @Test
    public void shouldReturnCorrectParametersForArticle3() throws IOException {
        String expectedTitle;
        String expectedAuthor;

        String actualTitle;
        String actualAuthor;

        File xmlFile3 = new File(Loader.getXmlArticle3());

        String xml3 = inputStreamToString(new FileInputStream(xmlFile3));
        Article article3 = xmlMapper.readValue(xml3, Article.class);
        logger.info(returnArticleWithCorrectValues(article3));

        expectedTitle = article3.getTitle();
        actualTitle = Loader.getTitleArticle3();

        expectedAuthor = article3.getAuthor();
        actualAuthor = Loader.getDefaultElemenent();

        assertEquals(expectedTitle, actualTitle);
        assertEquals(expectedAuthor, actualAuthor);
    }

    @Test
    public void shouldReturnCorrectParametersForArticle5() throws IOException {
        String expectedTitle;
        String expectedAuthor;

        String actualTitle;
        String actualAuthor;

        File xmlFile5 = new File(Loader.getXmlArticle5());

        String xml5 = inputStreamToString(new FileInputStream(xmlFile5));
        Article article5 = xmlMapper.readValue(xml5, Article.class);
        logger.info(returnArticleWithCorrectValues(article5));

        expectedTitle = article5.getTitle();
        actualTitle = Loader.getDefaultElemenent();

        expectedAuthor = article5.getAuthor();
        actualAuthor = Loader.getAuthorArticle5();

        assertEquals(expectedTitle, actualTitle);
        assertEquals(expectedAuthor, actualAuthor);
    }

    @Test
    public void shouldThrowParserException() throws ParserException {
        thrown.expect(ParserException.class);
        XMLparser.loadArticlesFromDirectory(Loader.getFailDirectory());
    }

}