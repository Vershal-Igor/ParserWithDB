package com.epam;

import com.epam.exception.ParserException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.stream.StreamSupport;


public final class Loader {
    private static final Logger logger = Logger.getLogger(Loader.class);
    private static final ResourceBundle rb = ResourceBundle.getBundle("properties/common");

    private static final String DIRECTORY = "DIRECTORY";
    private static final String TEST_DIRECTORY = "TEST_DIRECTORY";
    private static final String FAIL_DIRECTORY = "FAIL_DIRECTORY";

    private static final String DEFAULT_ELEMENT = "DEFAULT_ELEMENT";

    private static final String JSON_ARTICLE_1 = "JSON_ARTICLE_1";
    private static final String JSON_ARTICLE_4 = "JSON_ARTICLE_4";
    private static final String JSON_ARTICLE_6 = "JSON_ARTICLE_6";

    private static final String TXT_ARTICLE_7 = "TXT_ARTICLE_7";
    private static final String TXT_ARTICLE_8 = "TXT_ARTICLE_8";
    private static final String TXT_ARTICLE_9 = "TXT_ARTICLE_9";

    private static final String XML_ARTICLE_2 = "XML_ARTICLE_2";
    private static final String XML_ARTICLE_3 = "XML_ARTICLE_3";
    private static final String XML_ARTICLE_5 = "XML_ARTICLE_5";

    private static final String AUTHOR_ARTICLE_7 = "AUTHOR_ARTICLE_7";
    private static final String AUTHOR_ARTICLE_8 = "AUTHOR_ARTICLE_8";

    private static final String TITLE_ARTICLE_2 = "TITLE_ARTICLE_2";
    private static final String TITLE_ARTICLE_3 = "TITLE_ARTICLE_3";

    private static final String AUTHOR_ARTICLE_2 = "AUTHOR_ARTICLE_2";
    private static final String AUTHOR_ARTICLE_5 = "AUTHOR_ARTICLE_5";

    private static final String FILE_ENCODING = "FILE_ENCODING";

    private static final String PARSER_EXCEPTION = "PARSER_EXCEPTION";
    private static final String FNF_EXCEPTION = "FNF_EXCEPTION";
    private static final String IO_EXCEPTION = "IO_EXCEPTION";

    private static final Loader INSTANCE = new Loader();
    private static final String FORMAT_PATTERN = "{*.%s}";


    public static Loader getInstance() {
        return INSTANCE;
    }


    public static String getDirectory() {
        return rb.getString(DIRECTORY);
    }

    public static String getTestDirectory() {
        return rb.getString(TEST_DIRECTORY);
    }

    public static String getFailDirectory() {
        return rb.getString(FAIL_DIRECTORY);
    }

    public static String getDefaultElemenent() {
        return rb.getString(DEFAULT_ELEMENT);
    }

    public static String getJsonArticle1() {
        return rb.getString(JSON_ARTICLE_1);
    }

    public static String getJsonArticle4() {
        return rb.getString(JSON_ARTICLE_4);
    }

    public static String getJsonArticle6() {
        return rb.getString(JSON_ARTICLE_6);
    }

    public static String getTxtArticle7() {
        return rb.getString(TXT_ARTICLE_7);
    }

    public static String getTxtArticle8() {
        return rb.getString(TXT_ARTICLE_8);
    }

    public static String getTxtArticle9() {
        return rb.getString(TXT_ARTICLE_9);
    }

    public static String getXmlArticle2() {
        return rb.getString(XML_ARTICLE_2);
    }

    public static String getXmlArticle3() {
        return rb.getString(XML_ARTICLE_3);
    }

    public static String getXmlArticle5() {
        return rb.getString(XML_ARTICLE_5);
    }

    public static String getAuthorArticle7() {
        return rb.getString(AUTHOR_ARTICLE_7);
    }

    public static String getAuthorArticle8() {
        return rb.getString(AUTHOR_ARTICLE_8);
    }

    public static String getTitleArticle2() {
        return rb.getString(TITLE_ARTICLE_2);
    }

    public static String getTitleArticle3() {
        return rb.getString(TITLE_ARTICLE_3);
    }

    public static String getAuthorArticle2() {
        return rb.getString(AUTHOR_ARTICLE_2);
    }

    public static String getAuthorArticle5() {
        return rb.getString(AUTHOR_ARTICLE_5);
    }

    public static String getFileEncoding() {
        return rb.getString(FILE_ENCODING);
    }

    public static String getParserException() {
        return rb.getString(PARSER_EXCEPTION);
    }

    public static String getFnfException() {
        return rb.getString(FNF_EXCEPTION);
    }

    public static String getIoException() {
        return rb.getString(IO_EXCEPTION);
    }

    public String[] loadFilesFromDirectoryByType(String directory, String type) throws ParserException {
        String[] fileNames;

        Path path = Paths.get(directory);

        try {
            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path, String.format(FORMAT_PATTERN, type))) {

                fileNames = StreamSupport
                        .stream(directoryStream.spliterator(), false)
                        .map(Path::toString).toArray(String[]::new);
            }
            return fileNames;
        } catch (IOException e) {
            logger.error(IO_EXCEPTION, e);
            throw new ParserException(IO_EXCEPTION, e);
        }
    }

}


