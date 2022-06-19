package ru.digitalhabbits.homework2;

import java.util.Map;

/**
 * Counter of characters in a given string
 */
public interface LetterCounter {
    Map<Character, Long> count(String input);
}
