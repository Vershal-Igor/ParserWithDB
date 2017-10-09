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

        articleService.deleteAll();

        articleService.loadArticle(XMLparser.loadArticleFromFile(Loader.getXmlArticle2()));
        //articleService.delete(Loader.getTitleArticle2());

        articleService.loadArticles(XMLarticles);
        articleService.loadArticles(JSONarticles);
        articleService.loadArticles(TXTarticles);

        //articleService.delete(Loader.getTitleArticle3());

        articleService.loadArticle(XMLparser.loadArticleFromFile(Loader.getXmlArticle2()));

        articleService.findByTitle("Unknown");

        logger.info(articleService.findAll());

    }
}
