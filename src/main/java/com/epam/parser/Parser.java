package com.epam.parser;


import com.epam.entity.Article;
import jdk.nashorn.internal.runtime.ParserException;


import java.util.List;

public interface Parser {

    List<Article> loadArticlesFromDirectory(String directory) throws ParserException, com.epam.parser.exception.ParserException;

}
