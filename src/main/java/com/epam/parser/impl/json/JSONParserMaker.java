package com.epam.parser.impl.json;


import com.epam.parser.Parser;
import com.epam.parser.ParserMaker;

public class JSONParserMaker implements ParserMaker {
    @Override
    public Parser createParser() {
        return new JSONParser();
    }
}
