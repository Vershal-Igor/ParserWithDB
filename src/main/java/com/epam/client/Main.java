package com.epam.client;


import com.epam.entity.Article;
import com.epam.parser.Parser;
import com.epam.parser.ParserMaker;

import com.epam.parser.ParserType;

import com.epam.parser.exception.ParserException;
import com.epam.service.exception.ServiceException;
import com.epam.service.impl.ArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;


import java.util.List;

import static com.epam.parser.ParserMaker.getParserByName;


public class Main {
    private static final String DIRECTORY = "src/main/resources/files";


    public static void main(String[] args) throws ParserException, ServiceException {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");

        ArticleServiceImpl articleService = (ArticleServiceImpl) applicationContext.getBean("articleService");

        ParserMaker XMLmaker = getParserByName(ParserType.XML);
        Parser XMLparser = XMLmaker.createParser();
        List<Article> XMLarticles = XMLparser.loadArticlesFromDirectory(DIRECTORY);


        ParserMaker JSONmaker = getParserByName(ParserType.JSON);
        Parser JSONParser = JSONmaker.createParser();
        List<Article> JSONarticles = JSONParser.loadArticlesFromDirectory(DIRECTORY);


        ParserMaker TXTmaker = getParserByName(ParserType.TXT);
        Parser TXTParser = TXTmaker.createParser();
        List<Article> TXTarticles = TXTParser.loadArticlesFromDirectory(DIRECTORY);

        System.out.println(articleService.findAll());

        articleService.deleteAll();


        articleService.loadArticles(XMLarticles);
        articleService.loadArticles(JSONarticles);
        articleService.loadArticles(TXTarticles);

    }
}
