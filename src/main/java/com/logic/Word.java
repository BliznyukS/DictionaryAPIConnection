package com.logic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * "word" -> "hello"
 * "phonetic" -> "həˈləʊ"
 * "phonetics" -> {ArrayList@18500}  size = 2
 * "origin" -> "early 19th century: variant of earlier hollo ; related to holla."
 * "meanings" -> {ArrayList@18502}  size = 3
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Word {
    public String word;
    public String phonetic;
    public List<Phonetic> phonetics;
    public String origin;
    public List<Meaning> meanings;
}

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class Phonetic {
    public String text;
    public String audio;
}

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class Definition {
    public String definition;
    public String example;
    public List<String> synonyms;
    public List<String> antonyms;
}

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class Meaning {
    public String partOfSpeech;
    public List<Definition> definitions;
}
