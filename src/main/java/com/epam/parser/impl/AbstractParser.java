package com.epam.parser.impl;


import com.epam.entity.Article;
import com.epam.parser.Loader;
import com.epam.parser.Parser;
import com.epam.parser.exception.ParserException;

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
            List<Article> list = parse(name);
            articleList.addAll(list);
        }
        return articleList;
    }

    public Article loadArticleFromFile(String file) throws ParserException {
        List<Article> list = parse(file);
        return list.get(0);
    }

    protected abstract List<Article> parse(String directory) throws ParserException;
}
