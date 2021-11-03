package com.logic;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FreeDictionaryRestApi implements FreeDictionaryConnector {
    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<Word> getWordDescription(String word) {
        try {
            var responseEntity = restTemplate.getForEntity("https://api.dictionaryapi.dev/api/v2/entries/en/" + word, Word[].class);
            var words = responseEntity.getBody();
            return Arrays.asList(words);
        } catch (HttpClientErrorException e) {
            return Collections.emptyList();
        }
    }
}
