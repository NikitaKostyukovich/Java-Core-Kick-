package datareader;

import exception.ArrayException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataReaderImplTest {
    private DataReaderImpl reader;
    private Path tempFile;

    @BeforeEach
    void setUp() throws IOException {
        reader = new DataReaderImpl();
        tempFile = Files.createTempFile("test_data", ".txt");
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(tempFile);
    }

    @Test
    @DisplayName("Should read data from file")
    void shouldReadDataFromFile() throws ArrayException, IOException {

        try (FileWriter writer = new FileWriter(tempFile.toFile())) {
            writer.write("1, 2, 3\n");
            writer.write("4, 5, 6\n");
            writer.write("7, 8, 9\n");
        }

        List<String> lines = reader.readData(tempFile.toString());

        assertEquals(3, lines.size());
        assertEquals("1, 2, 3", lines.get(0));
        assertEquals("4, 5, 6", lines.get(1));
        assertEquals("7, 8, 9", lines.get(2));
    }

    @Test
    @DisplayName("Should throw exception for non-existent file")
    void shouldThrowExceptionForNonExistentFile() {
        assertThrows(ArrayException.class, () -> {
            reader.readData("non_existent_file.txt");
        });
    }

    @Test
    @DisplayName("Should read empty file")
    void shouldReadEmptyFile() throws ArrayException, IOException {

        Files.writeString(tempFile, "");

        List<String> lines = reader.readData(tempFile.toString());

        assertTrue(lines.isEmpty());
    }
}