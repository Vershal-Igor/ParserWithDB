package com.epam.impl.json;

import com.epam.Parser;
import com.epam.ParserMaker;

public class JSONParserMaker implements ParserMaker {
    @Override
    public Parser createParser() {
        return new JSONParser();
    }
}
