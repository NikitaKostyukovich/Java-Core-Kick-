package entity;

import exception.ArrayException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayEntityTest {
    private int[] testData;

    @BeforeEach
    void setUp() {
        testData = new int[]{1, 2, 3, 4, 5};
    }

    @Test
    @DisplayName("Should create ArrayEntity with valid data")
    void shouldCreateArrayEntityWithValidData() throws ArrayException {
        ArrayEntity entity = new ArrayEntity(testData, null, 1L);

        assertNotNull(entity);
        assertEquals(1L, entity.getId());
        assertEquals(5, entity.getLength());
        assertArrayEquals(testData, entity.getArray());
    }

    @Test
    @DisplayName("Should throw exception when data is null")
    void shouldThrowExceptionWhenDataIsNull() {
        assertThrows(ArrayException.class, () -> {
            new ArrayEntity(null, null, 1L);
        });
    }

    @Test
    @DisplayName("Should get element by valid index")
    void shouldGetElementByValidIndex() throws ArrayException {
        ArrayEntity entity = new ArrayEntity(testData, null, 1L);

        assertEquals(1, entity.getElement(0));
        assertEquals(3, entity.getElement(2));
        assertEquals(5, entity.getElement(4));
    }

    @Test
    @DisplayName("Should throw exception when index is out of bounds")
    void shouldThrowExceptionWhenIndexIsOutOfBounds() throws ArrayException {
        ArrayEntity entity = new ArrayEntity(testData, null, 1L);

        assertThrows(ArrayException.class, () -> entity.getElement(-1));
        assertThrows(ArrayException.class, () -> entity.getElement(5));
        assertThrows(ArrayException.class, () -> entity.getElement(100));
    }

    @Test
    @DisplayName("Should set element successfully")
    void shouldSetElementSuccessfully() throws ArrayException {
        ArrayEntity entity = new ArrayEntity(testData, null, 1L);

        entity.setElement(2, 100);

        assertEquals(100, entity.getElement(2));
    }

    @Test
    @DisplayName("Should throw exception when setting element with invalid index")
    void shouldThrowExceptionWhenSettingElementWithInvalidIndex() throws ArrayException {
        ArrayEntity entity = new ArrayEntity(testData, null, 1L);

        assertThrows(ArrayException.class, () -> entity.setElement(-1, 100));
        assertThrows(ArrayException.class, () -> entity.setElement(10, 100));
    }

    @Test
    @DisplayName("Should return defensive copy of array")
    void shouldReturnDefensiveCopyOfArray() throws ArrayException {
        ArrayEntity entity = new ArrayEntity(testData, null, 1L);

        int[] returnedArray = entity.getArray();
        returnedArray[0] = 999;

        assertEquals(1, entity.getElement(0));
    }

    @Test
    @DisplayName("Should work with empty array")
    void shouldWorkWithEmptyArray() throws ArrayException {
        int[] emptyArray = new int[0];
        ArrayEntity entity = new ArrayEntity(emptyArray, null, 1L);

        assertEquals(0, entity.getLength());
    }
}