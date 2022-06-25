package ru.digitalhabbits.homework2.impl;

import lombok.extern.slf4j.Slf4j;
import ru.digitalhabbits.homework2.LetterCountMerger;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class LetterCountMergerImpl implements LetterCountMerger {
    @Override
    public Map<Character, Long> merge(Map<Character, Long> first, Map<Character, Long> second) {
        log.info("Merging maps {} and {}", first, second);
        return Stream.concat(first.entrySet().stream(), second.entrySet().stream())
                .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.summingLong(Map.Entry::getValue)));
    }
}
