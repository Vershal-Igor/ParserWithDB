package com.epam.client;


import com.epam.entity.Article;
import com.epam.parser.Loader;
import com.epam.parser.Parser;
import com.epam.parser.ParserMaker;

import com.epam.parser.ParserType;

import com.epam.parser.exception.ParserException;
import com.epam.service.exception.ServiceException;
import com.epam.service.impl.ArticleServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import java.util.List;

import static com.epam.parser.ParserMaker.getParserByName;


public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);


    public static void main(String[] args) throws ParserException, ServiceException {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
        ArticleServiceImpl articleService = (ArticleServiceImpl) applicationContext.getBean("articleService");

        ParserMaker XMLmaker = getParserByName(ParserType.XML);
        Parser XMLparser = XMLmaker.createParser();
        List<Article> XMLarticles = XMLparser.loadArticlesFromDirectory(Loader.getDirectory());

        ParserMaker JSONmaker = getParserByName(ParserType.JSON);
        Parser JSONparser = JSONmaker.createParser();
        List<Article> JSONarticles = JSONparser.loadArticlesFromDirectory(Loader.getDirectory());

        ParserMaker TXTmaker = getParserByName(ParserType.TXT);
        Parser TXTparser = TXTmaker.createParser();
        List<Article> TXTarticles = TXTparser.loadArticlesFromDirectory(Loader.getDirectory());

        articleService.loadArticles(XMLarticles);
        articleService.loadArticles(JSONarticles);
        articleService.loadArticles(TXTarticles);

        logger.info(articleService.findAll());
        articleService.deleteAll();

    }
}
