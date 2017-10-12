package com.epam.parser.impl.json;


import com.epam.parser.Loader;
import com.epam.entity.Article;

import com.epam.parser.exception.ParserException;
import com.epam.parser.impl.AbstractParser;
import com.epam.parser.reader.Reader;
import com.epam.parser.reader.impl.JSONReader;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.apache.log4j.Logger;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONParser extends AbstractParser {
    private static final Logger logger = Logger.getLogger(JSONParser.class);
    private static final String TYPE = "json";

    public JSONParser() {
        super(TYPE);
    }

    @Override
    public Article loadArticleFromFile(String file) throws ParserException {
        Reader jsonReader = new JSONReader();
        Article article;
        try {
            article = jsonReader.read(new File(file));
        } catch (ParserException e) {
            logger.error(Loader.getParserException(), e);
            throw new ParserException(Loader.getParserException(), e);
        }
        System.out.println("---JSON---\n" + article);
        return article;
    }

}


