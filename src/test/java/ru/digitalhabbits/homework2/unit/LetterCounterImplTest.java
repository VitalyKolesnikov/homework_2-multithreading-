package ru.digitalhabbits.homework2.unit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.digitalhabbits.homework2.LetterCounterImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

class LetterCounterImplTest {

    @Test
    @DisplayName("Should return a map where key is letter and value is it`s count in a given string")
    void countLetters() {
        // given
        var inputString = "lorem ipsum dolor";
        var counter = new LetterCounterImpl();

        // when
        var resultMap = counter.count(inputString);

        // then
        assertThat(resultMap).containsOnly(
                entry('l', 2L),
                entry('o', 3L),
                entry('r', 2L),
                entry('e', 1L),
                entry('m', 2L),
                entry('i', 1L),
                entry('p', 1L),
                entry('s', 1L),
                entry('u', 1L),
                entry('d', 1L)
        );
    }

}
