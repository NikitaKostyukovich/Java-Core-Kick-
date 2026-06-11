package service.implement.arraystatsimpl;

import exception.ArrayException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ArrayStatsImplTest {
    private ArrayStatsImpl statsService;

    @BeforeEach
    void setUp() {
        statsService = new ArrayStatsImpl();
    }

    @Test
    @DisplayName("Should calculate sum of array")
    void shouldCalculateSumOfArray() throws ArrayException {
        int[] array = {1, 2, 3, 4, 5};
        Optional<Integer> sum = statsService.findSum(array);

        assertTrue(sum.isPresent());
        assertEquals(15, sum.get());
    }

    @Test
    @DisplayName("Should return empty Optional for empty array sum")
    void shouldReturnEmptyOptionalForEmptyArraySum() throws ArrayException {
        int[] emptyArray = {};
        Optional<Integer> sum = statsService.findSum(emptyArray);

        assertFalse(sum.isPresent());
    }

    @Test
    @DisplayName("Should find max value in array")
    void shouldFindMaxValueInArray() throws ArrayException {
        int[] array = {5, 2, 8, 1, 9, 3};
        Optional<Integer> max = statsService.findMax(array);

        assertTrue(max.isPresent());
        assertEquals(9, max.get());
    }

    @Test
    @DisplayName("Should find min value in array")
    void shouldFindMinValueInArray() throws ArrayException {
        int[] array = {5, 2, 8, 1, 9, 3};
        Optional<Integer> min = statsService.findMin(array);

        assertTrue(min.isPresent());
        assertEquals(1, min.get());
    }

    @Test
    @DisplayName("Should calculate average of array")
    void shouldCalculateAverageOfArray() throws ArrayException {
        int[] array = {2, 4, 6, 8};
        Optional<Double> average = statsService.findAverage(array);

        assertTrue(average.isPresent());
        assertEquals(5.0, average.get(), 0.001);
    }

    @Test
    @DisplayName("Should handle array with negative numbers")
    void shouldHandleArrayWithNegativeNumbers() throws ArrayException {
        int[] array = {-5, -2, -8, -1};

        Optional<Integer> sum = statsService.findSum(array);
        Optional<Integer> min = statsService.findMin(array);
        Optional<Integer> max = statsService.findMax(array);

        assertEquals(-16, sum.get());
        assertEquals(-8, min.get());
        assertEquals(-1, max.get());
    }

    @Test
    @DisplayName("Should handle array with single element")
    void shouldHandleArrayWithSingleElement() throws ArrayException {
        int[] array = {42};

        assertEquals(42, statsService.findSum(array).get());
        assertEquals(42, statsService.findMin(array).get());
        assertEquals(42, statsService.findMax(array).get());
        assertEquals(42.0, statsService.findAverage(array).get(), 0.001);
    }
}