package com.epam.deserializer;

import com.epam.entity.Article;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class CustomDeserializer extends JsonDeserializer<Article> {
    private static final String DEFAULT_ELEMENT = "UNKNOWN";
    private static final String NO_ELEMENT = " ";

    @Override
    public Article deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException{

        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        JsonNode articleNode = node.get("article");

        final String title = getCorrectElement(articleNode.get("title"));

        String authorName = getCorrectElement(articleNode.get("author_name"));

        return new Article(title, authorName);
    }

    private String getCorrectElement(JsonNode jsonNode) {
        if (jsonNode == null) {
            return NO_ELEMENT;
        }
        if (jsonNode.asText().isEmpty()) {
            return DEFAULT_ELEMENT;
        } else {
            return jsonNode.asText();
        }
    }
}




