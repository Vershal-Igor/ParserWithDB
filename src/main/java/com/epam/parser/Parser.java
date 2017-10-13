package com.epam.parser;


import com.epam.entity.Article;
import com.epam.parser.exception.ParserException;

import java.util.List;

public interface Parser {

    List<Article> loadArticlesFromDirectory(String directory) throws ParserException;

    Article loadArticleFromFile(String file) throws ParserException;
}
