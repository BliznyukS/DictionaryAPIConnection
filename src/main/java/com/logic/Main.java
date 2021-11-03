package com.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.csv.CsvSchema.Builder;


import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException {

        FreeDictionaryConnector freeDictionaryConnector = new FreeDictionaryRestApi();
        var parser = new FileParser();
        var knownWords = parser.parseKnownWordsFile("words/known.txt").keySet();
        var words = new ArrayList<Word>();

        knownWords.forEach(currentWordInLoop -> {
            var result = freeDictionaryConnector.getWordDescription(currentWordInLoop);
            words.addAll(result);
        });

        words.forEach(currentListOfWordsLoop -> {
            try {
                JsonNode jsonTree = new ObjectMapper().readTree(String.valueOf(words));

                Builder csvSchemaBuilder = CsvSchema.builder();
                JsonNode firstObject = jsonTree.elements().next();
                firstObject.fieldNames().forEachRemaining(fieldName -> {csvSchemaBuilder.addColumn(fieldName);} );
                CsvSchema csvSchema = csvSchemaBuilder.build().withHeader();

                CsvMapper csvMapper = new CsvMapper();
                csvMapper.writerFor(JsonNode.class)
                        .with(csvSchema)
                        .writeValue(new File("result.csv"), jsonTree);

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        System.out.println(words.size());
    }
}
