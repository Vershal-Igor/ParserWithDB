package com.epam.parser.impl;


import com.epam.entity.Article;
import com.epam.parser.Loader;
import com.epam.parser.Parser;
import com.epam.parser.exception.ParserException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public abstract class AbstractParser implements Parser {

    private final String type;

    protected AbstractParser(String type) {
        this.type = type;
    }

    public List<Article> loadArticlesFromDirectory(String directory) throws ParserException {
        List<Article> articleList = new ArrayList<>();

        String[] names = Loader.getInstance().loadFilesFromDirectoryByType(directory, type);

        for (String name : names) {
            Article list = loadArticleFromFile(name);
            articleList.add(list);
        }
        return articleList;
    }

}
