package com.epam.client;


import com.epam.Parser;
import com.epam.ParserMaker;

import com.epam.ParserType;
import com.epam.dao.exception.DAOException;
import com.epam.dao.impl.ArticleDAOImpl;
import com.epam.exception.ParserException;
import com.epam.service.exception.ServiceException;
import com.epam.service.impl.ArticleServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import java.io.IOException;


import static com.epam.ParserMaker.getParserByName;


public class Main {
    private static final String DIRECTORY = "src/main/resources/files";

    public static void main(String[] args) throws ParserException, DAOException, ServiceException {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
        for(String name: applicationContext.getBeanDefinitionNames())
            System.out.println(name);
        ArticleServiceImpl articleService = (ArticleServiceImpl) applicationContext.getBean("articleService");

        System.out.println(articleService.findAll());

        ParserMaker XMLmaker = getParserByName(ParserType.XML);
        Parser XMLparser = XMLmaker.createParser();
        XMLparser.loadArticlesFromDirectory(DIRECTORY);
        

        ParserMaker JSONmaker = getParserByName(ParserType.JSON);
        Parser JSONParser = JSONmaker.createParser();
        JSONParser.loadArticlesFromDirectory(DIRECTORY);


        ParserMaker TXTmaker = getParserByName(ParserType.TXT);
        Parser TXTParser = TXTmaker.createParser();
        TXTParser.loadArticlesFromDirectory(DIRECTORY);

    }
}
