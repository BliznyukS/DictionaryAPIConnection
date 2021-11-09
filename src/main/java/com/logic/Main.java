package com.logic;

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


        /**
         * Word,Phonetic,Meanings,Examples
         * hello,həˈləʊ,"exclamation - used as a greeting or to begin a phone conversation.
         * noun - an utterance of ‘hello’; a greeting.
         * verb - say or shout ‘hello’.","hello there, Katie!
         * she was getting polite nods and hellos from people
         * I pressed the phone button and helloed"
         *
         */


        File csvFile = CsvParser.formCsvFile(words);

        System.out.println(csvFile.toString());

        System.out.println(words.size());
    }
}
