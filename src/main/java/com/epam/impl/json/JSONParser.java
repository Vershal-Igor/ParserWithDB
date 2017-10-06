package com.epam.impl.json;

import com.epam.Loader;
import com.epam.deserializer.CustomDeserializer;
import com.epam.entity.Article;
import com.epam.exception.ParserException;
import com.epam.impl.AbstractParser;
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
    public List<Article> parse(String directory) throws ParserException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        List<Article> articles = new ArrayList<>();
        try {
            module.addDeserializer(Article.class, new CustomDeserializer());
            mapper.registerModule(module);
            Article article = mapper.readValue(new File(directory), Article.class);

            articles.add(article);

        } catch (IOException e) {
            logger.error(Loader.getParserException(), e);
            throw new ParserException(Loader.getParserException(), e);
        }
        System.out.println("---JSON---\n"+articles);
        return articles;
    }

}

