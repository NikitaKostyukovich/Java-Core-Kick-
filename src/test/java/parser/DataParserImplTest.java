package parser;

import exception.ArrayException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataParserImplTest {
    private DataParserImpl parser;

    @BeforeEach
    void setUp() {
        parser = new DataParserImpl();
    }

    @Test
    @DisplayName("Should parse line with comma separator")
    void shouldParseLineWithCommaSeparator() throws ArrayException {
        int[] result = parser.dataParse("1, 2, 3, 4, 5");

        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, result);
    }

    @Test
    @DisplayName("Should parse line with space separator")
    void shouldParseLineWithSpaceSeparator() throws ArrayException {
        int[] result = parser.dataParse("10 20 30");

        assertArrayEquals(new int[]{10, 20, 30}, result);
    }

    @Test
    @DisplayName("Should parse line with negative numbers")
    void shouldParseLineWithNegativeNumbers() throws ArrayException {
        int[] result = parser.dataParse("-1, -2, -3");

        assertArrayEquals(new int[]{-1, -2, -3}, result);
    }

    @Test
    @DisplayName("Should throw exception for null line")
    void shouldThrowExceptionForNullLine() {
        assertThrows(ArrayException.class, () -> parser.dataParse(null));
    }

    @Test
    @DisplayName("Should throw exception for blank line")
    void shouldThrowExceptionForBlankLine() {
        assertThrows(ArrayException.class, () -> parser.dataParse(""));
        assertThrows(ArrayException.class, () -> parser.dataParse("   "));
    }

    @Test
    @DisplayName("Should throw exception for invalid format")
    void shouldThrowExceptionForInvalidFormat() {
        assertThrows(ArrayException.class, () -> parser.dataParse("1, 2, abc"));
    }
}