package com.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FileParser {
    public FileParser() {
    }

    public Map<String, Long> parseKnownWordsFileHashMap(String filePath) throws URISyntaxException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader == null) {
            classLoader = Class.class.getClassLoader();
        }

        Map<String, Long> hashMap;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(classLoader.getResourceAsStream(filePath)))) {
            hashMap = reader.lines().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        }
        return hashMap;
    }
}
