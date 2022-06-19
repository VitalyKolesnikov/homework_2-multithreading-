package ru.digitalhabbits.homework2.unit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.digitalhabbits.homework2.LetterCountMergerImpl;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

class LetterCountMergerImplTest {

    @Test
    @DisplayName("Merge result should contain all keys of 2 source maps with summed values of their equal keys")
    void mergingTwoMaps() {
        // given
        Map<Character, Long> firstMap = new HashMap<>();
        firstMap.put('a', 1L);
        firstMap.put('b', 4L);
        firstMap.put('d', 2L);

        Map<Character, Long> secondMap = new HashMap<>();
        secondMap.put('a', 3L);
        secondMap.put('b', 1L);
        secondMap.put('c', 7L);
        secondMap.put('e', 12L);

        var merger = new LetterCountMergerImpl();

        // when
        var resultMap = merger.merge(firstMap, secondMap);

        // then
        assertThat(resultMap).containsOnly(
                entry('a', 4L),
                entry('b', 5L),
                entry('c', 7L),
                entry('d', 2L),
                entry('e', 12L)
        );
    }

}
