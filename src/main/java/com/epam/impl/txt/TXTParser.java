package com.epam.impl.txt;

import com.epam.Loader;
import com.epam.entity.Article;
import com.epam.exception.ParserException;
import com.epam.impl.AbstractParser;
import org.apache.log4j.Logger;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;


public class TXTParser extends AbstractParser {
    private static final Logger logger = Logger.getLogger(TXTParser.class);
    private static final String AUTHOR_PATERN = "Written by:";
    private static final String TYPE = "txt";


    public TXTParser() {
        super(TYPE);
    }

    @Override
    protected List<Article> parse(String directory) throws ParserException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(
                                new File(directory)),
                        Charset.forName(Loader.getFileEncoding())))) {

            String title = reader.readLine();
            String author = pullAuthorName(directory);

            ArrayList<Article> articles = new ArrayList<>();
            articles.add(new Article(title, author));
            System.out.println("---TXT---\n" + articles);
            return articles;
        } catch (FileNotFoundException e) {
            logger.error(Loader.getFnfException() + directory, e);
            throw new ParserException(Loader.getFnfException() + directory, e);
        } catch (IOException e) {
            logger.error(Loader.getIoException(), e);
            throw new ParserException(Loader.getIoException(), e);
        }
    }

    String pullAuthorName(String directory) throws ParserException {
        String author = Loader.getDefaultElemenent();
        try {
            Scanner scanner = new Scanner(new FileReader(directory));
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
            logger.error(Loader.getFnfException() + directory, e);
            throw new ParserException(Loader.getFnfException() + directory, e);
        }
        return author;
    }


}

