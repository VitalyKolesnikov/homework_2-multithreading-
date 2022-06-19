package ru.digitalhabbits.homework2;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LetterCounterImpl implements LetterCounter {
    @Override
    public Map<Character, Long> count(String input) {
        return input.chars()
                .mapToObj(chr -> (char) chr)
                .filter(chr -> chr != ' ')
                .collect(Collectors.groupingBy(Function.identity(), HashMap::new, Collectors.counting()));
    }
}
