package tools;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FilesProcessorTest {

    private static FilesProcessor filesProcessor;
    @BeforeAll
    public static void setup(){
        filesProcessor = new FilesProcessor();
    }

    @Test
     void readFileAsList() {
        List<List<String>> result = FilesProcessor.readFileAsList("/home/qwe/IdeaProjects/Restoran/test/com/company/resources/CommaSeparatedStrings");
        List<List<String>> expected = Arrays.asList(
                Arrays.asList("line 1 - item 1", "line 1 - item 2", "line 1 - item 3"),
                Arrays.asList("line 2 - item 1", "line 2 - item 2", "line 2 - item 3", "line 2 - item 4")
        );
        assertEquals(expected, result);
    }
}