package com.epam.service;



import com.epam.entity.Article;
import com.epam.service.exception.ServiceException;

import java.util.List;

public interface ArticleService extends GenericService<Article, Long> {
    void loadArticles(List<Article> articles) throws ServiceException;
}
