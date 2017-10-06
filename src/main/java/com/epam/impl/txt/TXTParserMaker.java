package com.epam.impl.txt;

import com.epam.Parser;
import com.epam.ParserMaker;

public class TXTParserMaker implements ParserMaker {
    @Override
    public Parser createParser() {
        return new TXTParser();
    }
}
