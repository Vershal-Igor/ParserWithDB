package com.epam.parser.impl.xml;


import com.epam.parser.Parser;
import com.epam.parser.ParserMaker;


public class XMLParserMaker implements ParserMaker {
    public Parser createParser() {
        return new XMLParser();
    }
}
