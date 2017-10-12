package com.epam.parser.impl.xml;

import com.epam.entity.Article;
import com.epam.parser.Loader;
import com.epam.parser.exception.ParserException;
import com.epam.parser.impl.AbstractParser;
import com.epam.parser.reader.Reader;
import com.epam.parser.reader.impl.XMLReader;
import org.apache.log4j.Logger;

import java.io.File;


public class XMLParser extends AbstractParser {
    private static final Logger logger = Logger.getLogger(XMLParser.class);
    private static final String TYPE = "xml";


    public XMLParser() {
        super(TYPE);
    }

    public Article loadArticleFromFile(String file) throws ParserException {
        Reader xmlReader = new XMLReader();
        Article article;
        try {
            article = xmlReader.read(new File(file));
        } catch (ParserException e) {
            logger.error(Loader.getParserException(), e);
            throw new ParserException(Loader.getParserException(), e);
        }
        System.out.println("---XML---\n" + article);
        return article;
    }

}
