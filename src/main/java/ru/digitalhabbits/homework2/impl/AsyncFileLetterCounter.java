package ru.digitalhabbits.homework2.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.digitalhabbits.homework2.FileLetterCounter;
import ru.digitalhabbits.homework2.FileReader;
import ru.digitalhabbits.homework2.LetterCountMerger;
import ru.digitalhabbits.homework2.LetterCounter;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@Slf4j
@RequiredArgsConstructor
public class AsyncFileLetterCounter implements FileLetterCounter {

    private final FileReader fileReader;
    private final LetterCounter letterCounter;
    private final LetterCountMerger letterCountMerger;
    private Map<Character, Long> resultMap;
    private final ExecutorService executorService;

    @Override
    public Map<Character, Long> count(File input) {

        resultMap = new ConcurrentHashMap<>();

        fileReader.readLines(input)
                .forEach(line -> {
                    log.info("Read line from file: {}", line);
                    supplyAsync(() -> letterCounter.count(line), executorService)
                            .thenAcceptAsync(map -> resultMap = letterCountMerger.merge(resultMap, map), executorService)
                            .join();
                });

        return resultMap;
    }

}
