package ru.digitalhabbits.homework2.impl;

import lombok.extern.slf4j.Slf4j;
import ru.digitalhabbits.homework2.FileLetterCounter;
import ru.digitalhabbits.homework2.FileReader;
import ru.digitalhabbits.homework2.LetterCountMerger;
import ru.digitalhabbits.homework2.LetterCounter;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@Slf4j
public class AsyncFileLetterCounter implements FileLetterCounter {

    private final FileReader fileReader = new FileReaderImpl();
    private final LetterCounter letterCounter = new LetterCounterImpl();
    private final LetterCountMerger letterCountMerger = new LetterCountMergerImpl();
    private Map<Character, Long> resultMap;
    private final ExecutorService executorService = Executors.newFixedThreadPool(8);

    @Override
    public Map<Character, Long> count(File input) {

        // так тоже работает
//        return fileReader.readLines(input).parallel()
//                .map(letterCounter::count)
//                .reduce(letterCountMerger::merge)
//                .orElse(Collections.emptyMap());

        resultMap = new HashMap<>();

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
