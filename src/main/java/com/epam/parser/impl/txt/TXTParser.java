package com.epam.parser.impl.txt;

import com.epam.parser.Loader;
import com.epam.entity.Article;
import com.epam.parser.exception.ParserException;
import com.epam.parser.impl.AbstractParser;
import com.epam.parser.reader.Reader;
import com.epam.parser.reader.impl.TXTReader;
import org.apache.log4j.Logger;

import java.io.*;


public class TXTParser extends AbstractParser {
    private static final Logger logger = Logger.getLogger(TXTParser.class);
    private static final String TYPE = "txt";


    public TXTParser() {
        super(TYPE);
    }

    @Override
    public Article loadArticleFromFile(String file) throws ParserException {
        Reader txtReader = new TXTReader();
        Article article;
        try {
            article = txtReader.read(new File(file));
            System.out.println("---TXT---\n" + article);
            return article;
        } catch (ParserException e) {
            logger.error(Loader.getParserException() + file, e);
            throw new ParserException(Loader.getParserException() + file, e);
        }
    }

}

