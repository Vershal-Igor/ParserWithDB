package com.epam.parser.reader;


import com.epam.entity.Article;
import com.epam.parser.exception.ParserException;

import java.io.File;

public interface Reader {
    Article read(File file) throws ParserException;
}
