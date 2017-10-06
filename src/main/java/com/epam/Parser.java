package com.epam;


import com.epam.entity.Article;
import com.epam.exception.ParserException;

import java.util.List;

public interface Parser {

    List<Article> loadArticlesFromDirectory(String directory) throws ParserException;

}
