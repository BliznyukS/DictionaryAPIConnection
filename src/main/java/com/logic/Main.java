package com.logic;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

        System.out.println(words.size());
    }
}
