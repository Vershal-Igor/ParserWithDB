package com.epam.parser;


import com.epam.parser.impl.json.JSONParserMaker;
import com.epam.parser.impl.txt.TXTParserMaker;
import com.epam.parser.impl.xml.XMLParserMaker;
import org.apache.log4j.Logger;

public interface ParserMaker {
    Logger logger = Logger.getLogger(ParserMaker.class);
    String EXCEPTION = "Can't find such type: ";

    Parser createParser();

    static ParserMaker getParserByName(ParserType type) {
        switch (type) {
            case XML:
                return new XMLParserMaker();
            case JSON:
                return new JSONParserMaker();
            case TXT:
                return new TXTParserMaker();
            default:
                logger.error(EXCEPTION + type);
                throw new IllegalArgumentException(EXCEPTION + type);
        }
    }
}
