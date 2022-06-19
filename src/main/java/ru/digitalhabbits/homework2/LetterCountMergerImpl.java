package ru.digitalhabbits.homework2;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LetterCountMergerImpl implements LetterCountMerger {
    @Override
    public Map<Character, Long> merge(Map<Character, Long> first, Map<Character, Long> second) {
        return Stream.concat(first.entrySet().stream(), second.entrySet().stream())
                .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.summingLong(Map.Entry::getValue)));
    }
}
