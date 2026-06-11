package warehouse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseTest {
    private Warehouse warehouse;

    @BeforeEach
    void setUp() {
        warehouse = Warehouse.getInstance();
    }

    @Test
    @DisplayName("Should return same instance (Singleton)")
    void shouldReturnSameInstance() {
        Warehouse instance1 = Warehouse.getInstance();
        Warehouse instance2 = Warehouse.getInstance();

        assertSame(instance1, instance2);
    }

    @Test
    @DisplayName("Should save and retrieve metrics")
    void shouldSaveAndRetrieveMetrics() {
        ArrayMetrics metrics = new ArrayMetrics(1L, 100, 10, 50, 25.5);

        warehouse.saveMetrics(1L, metrics);
        ArrayMetrics retrieved = warehouse.getMetrics(1L);

        assertNotNull(retrieved);
        assertEquals(1L, retrieved.getId());
        assertEquals(100, retrieved.getSum());
        assertEquals(10, retrieved.getMin());
        assertEquals(50, retrieved.getMax());
        assertEquals(25.5, retrieved.getAverage(), 0.001);
    }

    @Test
    @DisplayName("Should return null for non-existent id")
    void shouldReturnNullForNonExistentId() {
        ArrayMetrics metrics = warehouse.getMetrics(999L);

        assertNull(metrics);
    }

    @Test
    @DisplayName("Should overwrite existing metrics")
    void shouldOverwriteExistingMetrics() {
        ArrayMetrics metrics1 = new ArrayMetrics(1L, 100, 10, 50, 25.5);
        ArrayMetrics metrics2 = new ArrayMetrics(1L, 200, 20, 100, 50.0);

        warehouse.saveMetrics(1L, metrics1);
        warehouse.saveMetrics(1L, metrics2);

        ArrayMetrics retrieved = warehouse.getMetrics(1L);
        assertEquals(200, retrieved.getSum());
        assertEquals(20, retrieved.getMin());
    }
}