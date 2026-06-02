package integration;

import datareader.DataReaderImpl;
import entity.ArrayEntity;
import factory.EntityFactoryBuilding;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import parser.DataParserImp;
import service.implement.arraysortsimpl.BubbleSortImpl;
import service.implement.arraystatsimpl.ArrayStatsImpl;
import validator.DataValidatorImpl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class IntegrationTest {

    private DataReaderImpl dataReader;
    private DataValidatorImpl dataValidator;
    private DataParserImp dataParser;
    private EntityFactoryBuilding entityFactory;
    private BubbleSortImpl bubbleSort;
    private ArrayStatsImpl arrayStats;

    @BeforeEach
    void setUp() {
        dataReader = new DataReaderImpl();
        dataValidator = new DataValidatorImpl();
        dataParser = new DataParserImp();
        entityFactory = new EntityFactoryBuilding();
        bubbleSort = new BubbleSortImpl();
        arrayStats = new ArrayStatsImpl();
    }

    @Test
    @DisplayName("Complete pipeline integration: read, validate, parse, create entity, sort, and calculate stats")
    void testCompletePipeline(@TempDir Path tempDir) throws Exception {
        String filePath = "data/data.txt";


        List<String> lines = dataReader.readData(filePath);
        assertNotNull(lines, "List of lines should not be null");
        assertFalse(lines.isEmpty(), "File should not be empty");


        String line = lines.get(0);


        assertTrue(dataValidator.isValid(line), "First line should be valid");

        int[] rawArray = dataParser.dataParse(line);
        ArrayEntity entity = entityFactory.build(rawArray);
        assertNotNull(entity);
        assertNotNull(entity.getArray());

        bubbleSort.sort(entity.getArray());

        Optional<Integer> max = arrayStats.findMax(entity.getArray());
        assertTrue(max.isPresent());
        assertEquals(30, max.get());

        Optional<Integer> min = arrayStats.findMin(entity.getArray());
        assertTrue(min.isPresent());
        assertEquals(2, min.get());

        Optional<Double> avg = arrayStats.findAverage(entity.getArray());
        assertTrue(avg.isPresent());
        assertEquals(12.4, avg.get(), 0.001);
    }
}