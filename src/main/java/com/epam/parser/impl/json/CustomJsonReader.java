package com.epam.parser.impl.json;

import com.epam.entity.Article;
import com.epam.parser.Loader;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class CustomJsonReader extends JsonDeserializer<Article> {

    @Override
    public Article deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        JsonNode articleNode = node.get("article");

        final String title = getCorrectElement(articleNode.get("title"));

        String authorName = getCorrectElement(articleNode.get("author_name"));

        String contents = getCorrectElement(articleNode.get("content"));
        return new Article(title, authorName, contents);
    }

    private String getCorrectElement(JsonNode jsonNode) {
        if (jsonNode == null) {
            return Loader.getDefaultElemenent();
        }
        if (jsonNode.asText().isEmpty()) {
            return Loader.getDefaultElemenent();
        } else {
            return jsonNode.asText();
        }
    }
}




