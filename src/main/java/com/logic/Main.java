package com.logic;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException {

        FileParser parser = new FileParser();
        HashMap<String, Long> knownWords = (HashMap<String, Long>) parser.parseKnownWordsFile("words/known.txt");
        System.out.println(knownWords.size());
//
//
//
//        String currentWord = "case";
//        Map<String, Long> knownWords = new HashMap<>();
//        ResponseEntity<Object> responseEntity = restTemplate.getForEntity("https://api.dictionaryapi.dev/api/v2/entries/en/" + currentWord, Object.class);
//    }
//
//    @AllArgsConstructor
//
//      Gson почитать
//
//    class Word {
//        String word;
//        String phonetic;
//        ArrayList<Object> phonetics;
//        String origin;
//        ArrayList<Object> meanings;
//    }
    }
}
