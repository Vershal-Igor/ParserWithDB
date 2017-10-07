package com.epam.parser.impl.txt;

import com.epam.parser.Parser;
import com.epam.parser.ParserMaker;

public class TXTParserMaker implements ParserMaker {
    @Override
    public Parser createParser() {
        return new TXTParser();
    }
}
