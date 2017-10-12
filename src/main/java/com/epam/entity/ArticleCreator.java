package com.epam.entity;


public class ArticleCreator {
    private String title;
    private String author;
    private String contents;

    public ArticleCreator setTitle(String title) {
        this.title = title;
        return this;
    }

    public ArticleCreator setAuthor(String author) {
        this.author = author;
        return this;
    }

    public ArticleCreator setContents(String contents) {
        this.contents = contents;
        return this;
    }

    public Article create() {
        Article article = new Article();
        article.setTitle(title);
        article.setAuthor(author);
        article.setContents(contents);

        return article;
    }
}

