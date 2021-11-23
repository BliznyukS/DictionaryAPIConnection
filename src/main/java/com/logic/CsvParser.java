package com.logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvParser {

    private static final String EMPTY_STRING = "";
    private static final String COMMA_SEPARATOR = ",";
    private static final String REGEX = "\\R";
    private static final String SPACE = " ";
    private static final String SINGLE_QUOTE = "'";
    private static final String QUOTE_ESCAPE = "\"";
    private static final String DOUBLE_QUOTE_ESCAPE = "\"\"";

    public static File formCsvFile(Collection<Word> knownWordsFromDictionaryApi) {
        File csvOutputFile = new File("result.csv");
        try (var printWriter = new PrintWriter(csvOutputFile)) {
            printWriter.println(convertToCSV(new String[]{"word", "phonetic", "origin", "meanings", "examples"}));

            for (Word e : knownWordsFromDictionaryApi) {
                String[] strings = new String[]{e.getWord(), e.getPhonetic(), e.getOrigin(), "meanings", "examples"};
                String s = convertToCSV(strings);
                printWriter.println(s);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
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
