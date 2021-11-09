package com.logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvParser {

    private static final String FILE_NAME = "result";
    private static final String CSV_SUFFIX = ".csv";
    private static final String EMPTY_STRING = "";
    private static final String COMMA_SEPARATOR = ",";
    private static final String REGEX = "\\R";
    private static final String SPACE = " ";
    private static final String SINGLE_QUOTE = "'";
    private static final String QUOTE_ESCAPE = "\"";
    private static final String DOUBLE_QUOTE_ESCAPE = "\"\"";

    public static File formCsvFile(Collection<Word> knownWordsFromDictionaryApi) {
        File csvOutputFile = null;
        try {
            csvOutputFile = File.createTempFile(FILE_NAME, CSV_SUFFIX);
        } catch (IOException e) {
//            log.debug("Could not create a CSV file ", e);
//            log.error(EMAIL_REPORT_WAS_NOT_SENT_CANT_CREATE_CSV + e.getMessage());
        }
        try (var printWriter = new PrintWriter(csvOutputFile)) {
            printWriter.println(convertToCSV(new String[]{"word", "phonetic", "meanings", "examples"}));

            for (Word e : knownWordsFromDictionaryApi) {
                String[] strings = new String[]{e.getWord(), e.getPhonetic(), "meanings", "examples"};
                String s = convertToCSV(strings);
                printWriter.println(s);
            }

        } catch (FileNotFoundException e) {
//            log.debug("FileNotFoundException ", e);
//            log.error(EMAIL_REPORT_WAS_NOT_SENT_CANT_CREATE_CSV + e.getMessage());
        }
        return csvOutputFile;
    }

    private static String convertToCSV(String[] data) {
        return Stream.of(data)
                .map(CsvParser::escapeSpecialCharacters)
                .collect(Collectors.joining(COMMA_SEPARATOR));
    }

    private static String escapeSpecialCharacters(String data) {
        if (Objects.isNull(data)) return EMPTY_STRING;
        var escapedData = data.replaceAll(REGEX, SPACE);
        if (data.contains(COMMA_SEPARATOR) || data.contains(QUOTE_ESCAPE) || data.contains(SINGLE_QUOTE)) {
            data = data.replace(QUOTE_ESCAPE, DOUBLE_QUOTE_ESCAPE);
            escapedData = QUOTE_ESCAPE + data + QUOTE_ESCAPE;
        }
        return escapedData;
    }
}
