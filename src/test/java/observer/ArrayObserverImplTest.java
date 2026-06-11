package observer;

import entity.ArrayEntity;
import exception.ArrayException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import repository.ArrayRepositoryImpl;
import service.implement.arraystatsimpl.ArrayStatsImpl;
import warehouse.ArrayMetrics;
import warehouse.Warehouse;

import static org.junit.jupiter.api.Assertions.*;

class ArrayObserverImplTest {
    private ArrayObserverImpl observer;
    private ArrayRepositoryImpl repository;
    private ArrayStatsImpl statsService;
    private Warehouse warehouse;

    @BeforeEach
    void setUp() {
        repository = ArrayRepositoryImpl.getInstance();
        statsService = new ArrayStatsImpl();
        warehouse = Warehouse.getInstance();
        observer = new ArrayObserverImpl(repository, statsService, warehouse);
    }

    @Test
    @DisplayName("Should update metrics when entity exists")
    void shouldUpdateMetricsWhenEntityExists() throws ArrayException {
        int[] data = {1, 2, 3, 4, 5};
        ArrayEntity entity = new ArrayEntity(data, null, 100L);

        repository.add(entity);
        observer.update(100L);

        ArrayMetrics metrics = warehouse.getMetrics(100L);
        assertNotNull(metrics);
        assertEquals(15, metrics.getSum());
        assertEquals(1, metrics.getMin());
        assertEquals(5, metrics.getMax());
        assertEquals(3.0, metrics.getAverage(), 0.001);
    }

    @Test
    @DisplayName("Should not update metrics for empty array")
    void shouldNotUpdateMetricsForEmptyArray() throws ArrayException {
        int[] emptyData = {};
        ArrayEntity entity = new ArrayEntity(emptyData, null, 200L);

        repository.add(entity);
        observer.update(200L);

        ArrayMetrics metrics = warehouse.getMetrics(200L);
        assertNull(metrics);
    }
}