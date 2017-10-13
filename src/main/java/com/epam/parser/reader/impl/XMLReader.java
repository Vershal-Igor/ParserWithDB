package com.epam.parser.reader.impl;


import com.epam.entity.Article;
import com.epam.parser.Loader;
import com.epam.parser.exception.ParserException;
import com.epam.parser.reader.Reader;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.log4j.Logger;

import java.io.*;


public class XMLReader implements Reader {
    private static final Logger logger = Logger.getLogger(XMLReader.class);

    @Override
    public Article read(File file) throws ParserException {
        Article value;
        Article article;
        XmlMapper xmlMapper = new XmlMapper();
        try {
            String xml = inputStreamToString(new FileInputStream(file));
            value = xmlMapper.readValue(xml, Article.class);
            article = returnArticleWithCorrectValues(value);
        } catch (IOException e) {
            logger.error(Loader.getParserException(), e);
            throw new ParserException(Loader.getParserException(), e);
        }
        return article;
    }

    private Article returnArticleWithCorrectValues(Article value) {

        if (value.getTitle() == null) {
            value.setTitle(Loader.getDefaultElemenent());
        }

        if (value.getAuthor() == null) {
            value.setAuthor(Loader.getDefaultElemenent());
        }
        return value;
    }

     private String inputStreamToString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        while ((line = br.readLine()) != null) {
            sb.append(line.trim());
        }
        br.close();
        return sb.toString();
    }
}
