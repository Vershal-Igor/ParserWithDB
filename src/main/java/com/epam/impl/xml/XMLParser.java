package com.epam.impl.xml;


import com.epam.Loader;
import com.epam.entity.Article;
import com.epam.exception.ParserException;
import com.epam.impl.AbstractParser;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class XMLParser extends AbstractParser {
    private static final Logger logger = Logger.getLogger(XMLParser.class);
    private static final String TYPE = "xml";


    public XMLParser() {
        super(TYPE);
    }

    public List<Article> parse(String directory) throws ParserException {
        List<Article> articles = new ArrayList<>();
        File xmlFile = new File(directory);
        XmlMapper xmlMapper = new XmlMapper();
        try {
            String xml = inputStreamToString(new FileInputStream(xmlFile));
            Article value = xmlMapper.readValue(xml, Article.class);
            articles.add(returnArticleWithCorrectValues(value));
        } catch (IOException e) {
            logger.error(Loader.getParserException(), e);
            throw new ParserException(Loader.getParserException(), e);
        }
        System.out.println("---XML---\n"+articles);
        return articles;
    }

    static Article returnArticleWithCorrectValues(Article value) {

        if (value.getTitle() == null) {
            value.setTitle(Loader.getDefaultElemenent());
        }

        if (value.getAuthor() == null) {
            value.setAuthor(Loader.getDefaultElemenent());
        }
        return value;
    }

    static String inputStreamToString(InputStream is) throws IOException {
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
