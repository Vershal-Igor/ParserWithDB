package com.epam.parser.reader.impl;

import com.epam.entity.Article;
import com.epam.parser.Loader;
import com.epam.parser.exception.ParserException;
import com.epam.parser.reader.Reader;
import org.apache.log4j.Logger;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Scanner;

public class TXTReader implements Reader {
    private static final Logger logger = Logger.getLogger(TXTReader.class);
    private static final String AUTHOR_PATERN = "Written by:";

    @Override
    public Article read(File file) throws ParserException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(file),
                        Charset.forName(Loader.getFileEncoding())))) {

            String title = reader.readLine();
            String author = pullAuthorName(file);

            return new Article(title, author);
        } catch (IOException e) {
            logger.error(Loader.getIoException() + file, e);
            throw new ParserException(Loader.getIoException() + file, e);
        }
    }

    String pullAuthorName(File file) throws ParserException {
        String author = Loader.getDefaultElemenent();
        try {
            Scanner scanner = new Scanner(new FileReader(file));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith(AUTHOR_PATERN)) {
                    String[] array = line.split(": ");
                    if (array.length == 2) {
                        author = array[1].trim();
                    } else {
                        author = Loader.getDefaultElemenent();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            logger.error(Loader.getFnfException() + file, e);
            throw new ParserException(Loader.getFnfException() + file, e);
        }
        return author;
    }
}
