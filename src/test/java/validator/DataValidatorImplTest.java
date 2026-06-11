package validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataValidatorImplTest {
    private DataValidatorImpl validator;

    @BeforeEach
    void setUp() {
        validator = new DataValidatorImpl();
    }

    @Test
    @DisplayName("Should validate correct line with comma separator")
    void shouldValidateCorrectLineWithCommaSeparator() {
        assertTrue(validator.isValid("1, 2, 3, 4, 5"));
    }

    @Test
    @DisplayName("Should validate correct line with space separator")
    void shouldValidateCorrectLineWithSpaceSeparator() {
        assertTrue(validator.isValid("1 2 3 4 5"));
    }

    @Test
    @DisplayName("Should validate correct line with semicolon separator")
    void shouldValidateCorrectLineWithSemicolonSeparator() {
        assertTrue(validator.isValid("1; 2; 3; 4; 5"));
    }

    @Test
    @DisplayName("Should validate line with negative numbers")
    void shouldValidateLineWithNegativeNumbers() {
        assertTrue(validator.isValid("-1, -2, -3"));
    }

    @Test
    @DisplayName("Should reject null line")
    void shouldRejectNullLine() {
        assertFalse(validator.isValid(null));
    }

    @Test
    @DisplayName("Should reject empty line")
    void shouldRejectEmptyLine() {
        assertFalse(validator.isValid(""));
        assertFalse(validator.isValid("   "));
    }

    @Test
    @DisplayName("Should reject line with letters")
    void shouldRejectLineWithLetters() {
        assertFalse(validator.isValid("1, 2, abc, 4"));
    }

    @Test
    @DisplayName("Should reject line with special characters")
    void shouldRejectLineWithSpecialCharacters() {
        assertFalse(validator.isValid("1, 2, @, 4"));
    }
}