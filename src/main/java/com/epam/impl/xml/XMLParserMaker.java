package com.epam.impl.xml;


import com.epam.Parser;
import com.epam.ParserMaker;


public class XMLParserMaker implements ParserMaker {
    public Parser createParser() {
        return new XMLParser();
    }
}
