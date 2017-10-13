package com.epam.parser.reader.impl;


import com.epam.entity.Article;
import com.epam.parser.Loader;
import com.epam.parser.exception.ParserException;
import com.epam.parser.reader.Reader;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class JSONReader implements Reader {
    private static final Logger logger = Logger.getLogger(JSONReader.class);

    @Override
    public Article read(File file) throws ParserException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Article.class, new CustomJsonReader());
        mapper.registerModule(module);

        try {
            return mapper.readValue(file, Article.class);
        } catch (IOException e) {
            logger.error(Loader.getIoException(), e);
            throw new ParserException(Loader.getIoException(), e);
        }
    }
}

