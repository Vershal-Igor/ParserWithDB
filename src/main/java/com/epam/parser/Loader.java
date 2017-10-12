package com.epam.parser;


import com.epam.parser.exception.ParserException;
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
    private static final ResourceBundle RB = ResourceBundle.getBundle("properties/common");

    private static final String DIRECTORY = "DIRECTORY";
    private static final String TEST_DIRECTORY = "TEST_DIRECTORY";
    private static final String FAIL_DIRECTORY = "FAIL_DIRECTORY";

    private static final String DEFAULT_ELEMENT = "DEFAULT_ELEMENT";

    private static final String JSON_ARTICLE_1 = "JSON_ARTICLE_1";
    private static final String JSON_ARTICLE_2 = "JSON_ARTICLE_2";
    private static final String JSON_ARTICLE_3 = "JSON_ARTICLE_3";

    private static final String TXT_ARTICLE_1 = "TXT_ARTICLE_1";
    private static final String TXT_ARTICLE_2 = "TXT_ARTICLE_2";
    private static final String TXT_ARTICLE_3 = "TXT_ARTICLE_3";

    private static final String XML_ARTICLE_1 = "XML_ARTICLE_1";
    private static final String XML_ARTICLE_2 = "XML_ARTICLE_2";
    private static final String XML_ARTICLE_3 = "XML_ARTICLE_3";

    private static final String AUTHOR_ARTICLE_7 = "AUTHOR_ARTICLE_7";
    private static final String AUTHOR_ARTICLE_8 = "AUTHOR_ARTICLE_8";

    private static final String TITLE_ARTICLE_1 = "TITLE_ARTICLE_1";
    private static final String TITLE_ARTICLE_2 = "TITLE_ARTICLE_2";
    private static final String TITLE_ARTICLE_3 = "TITLE_ARTICLE_3";
    private static final String TITLE_ARTICLE_4 = "TITLE_ARTICLE_4";
    private static final String TITLE_ARTICLE_5 = "TITLE_ARTICLE_5";
    private static final String TITLE_ARTICLE_6 = "TITLE_ARTICLE_6";
    private static final String TITLE_ARTICLE_7 = "TITLE_ARTICLE_7";
    private static final String TITLE_ARTICLE_8 = "TITLE_ARTICLE_8";
    private static final String TITLE_ARTICLE_9 = "TITLE_ARTICLE_9";

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
        return RB.getString(DIRECTORY);
    }

    public static String getTestDirectory() {
        return RB.getString(TEST_DIRECTORY);
    }

    public static String getFailDirectory() {
        return RB.getString(FAIL_DIRECTORY);
    }

    public static String getDefaultElemenent() {
        return RB.getString(DEFAULT_ELEMENT);
    }

    public static String getJsonArticle1() {
        return RB.getString(JSON_ARTICLE_1);
    }

    public static String getJsonArticle2() {
        return RB.getString(JSON_ARTICLE_2);
    }

    public static String getJsonArticle3() {
        return RB.getString(JSON_ARTICLE_3);
    }

    public static String getTxtArticle1() {
        return RB.getString(TXT_ARTICLE_1);
    }

    public static String getTxtArticle2() {
        return RB.getString(TXT_ARTICLE_2);
    }

    public static String getTxtArticle3() {
        return RB.getString(TXT_ARTICLE_3);
    }

    public static String getXmlArticle1() {
        return RB.getString(XML_ARTICLE_1);
    }

    public static String getXmlArticle2() {
        return RB.getString(XML_ARTICLE_2);
    }

    public static String getXmlArticle3() {
        return RB.getString(XML_ARTICLE_3);
    }

    public static String getAuthorArticle7() {
        return RB.getString(AUTHOR_ARTICLE_7);
    }

    public static String getAuthorArticle8() {
        return RB.getString(AUTHOR_ARTICLE_8);
    }

    public static String getTitleArticle1() {
        return RB.getString(TITLE_ARTICLE_1);
    }

    public static String getTitleArticle2() {
        return RB.getString(TITLE_ARTICLE_2);
    }

    public static String getTitleArticle3() {
        return RB.getString(TITLE_ARTICLE_3);
    }

    public static String getTitleArticle4() {
        return RB.getString(TITLE_ARTICLE_4);
    }

    public static String getTitleArticle5() {
        return RB.getString(TITLE_ARTICLE_5);
    }

    public static String getTitleArticle6() {
        return RB.getString(TITLE_ARTICLE_6);
    }

    public static String getTitleArticle7() {
        return RB.getString(TITLE_ARTICLE_7);
    }

    public static String getTitleArticle8() {
        return RB.getString(TITLE_ARTICLE_8);
    }

    public static String getTitleArticle9() {
        return RB.getString(TITLE_ARTICLE_9);
    }

    public static String getAuthorArticle2() {
        return RB.getString(AUTHOR_ARTICLE_2);
    }

    public static String getAuthorArticle5() {
        return RB.getString(AUTHOR_ARTICLE_5);
    }

    public static String getFileEncoding() {
        return RB.getString(FILE_ENCODING);
    }

    public static String getParserException() {
        return RB.getString(PARSER_EXCEPTION);
    }

    public static String getFnfException() {
        return RB.getString(FNF_EXCEPTION);
    }

    public static String getIoException() {
        return RB.getString(IO_EXCEPTION);
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



