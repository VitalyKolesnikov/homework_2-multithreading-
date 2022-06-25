package ru.digitalhabbits.homework2.impl;

import lombok.extern.slf4j.Slf4j;
import ru.digitalhabbits.homework2.LetterCounter;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public class LetterCounterImpl implements LetterCounter {
    @Override
    public Map<Character, Long> count(String input) {
        log.info("Counting chars in string: " + input);
        return input.chars()
                .mapToObj(chr -> (char) chr)
                .filter(chr -> chr != ' ')
                .collect(Collectors.groupingBy(Function.identity(), HashMap::new, Collectors.counting()));
    }
}
