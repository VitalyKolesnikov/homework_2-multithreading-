package ru.digitalhabbits.homework2.unit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.digitalhabbits.homework2.impl.FileReaderImpl;

import java.io.File;

import static com.google.common.io.Resources.getResource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FileReaderImplTest {

    @Test
    @DisplayName("Should read lines from a file")
    void readFromFile() {
        // given
        var file = getFile("test.txt");
        FileReaderImpl fileReader = new FileReaderImpl();

        // when
        var result = fileReader.readLines(file);

        // then
        assertEquals(1000, result.count());
    }

    private File getFile(String name) {
        return new File(getResource(name).getPath());
    }

}
