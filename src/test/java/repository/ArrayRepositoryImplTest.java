package repository;

import entity.ArrayEntity;
import exception.ArrayException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import warehouse.ArrayMetrics;
import warehouse.Warehouse;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ArrayRepositoryImplTest {
    private ArrayRepositoryImpl repository;
    private Warehouse warehouse;

    @BeforeEach
    void setUp() {
        repository = ArrayRepositoryImpl.getInstance();
        warehouse = Warehouse.getInstance();
        repository.clear();
    }

    @Test
    @DisplayName("Should add entity to repository")
    void shouldAddEntityToRepository() throws ArrayException {
        ArrayEntity entity = new ArrayEntity(new int[]{1, 2, 3}, null, 1L);

        repository.add(entity);

        Optional<ArrayEntity> found = repository.findById(1L);
        assertTrue(found.isPresent());
        assertEquals(1L, found.get().getId());
    }

    @Test
    @DisplayName("Should remove entity by id")
    void shouldRemoveEntityById() throws ArrayException {
        ArrayEntity entity = new ArrayEntity(new int[]{1, 2, 3}, null, 2L);
        repository.add(entity);

        boolean removed = repository.removeById(2L);

        assertTrue(removed);
        assertFalse(repository.findById(2L).isPresent());
    }

    @Test
    @DisplayName("Should return false when removing non-existent id")
    void shouldReturnFalseWhenRemovingNonExistentId() {
        boolean removed = repository.removeById(999L);

        assertFalse(removed);
    }

    @Test
    @DisplayName("Should find entity by id")
    void shouldFindEntityById() throws ArrayException {
        ArrayEntity entity = new ArrayEntity(new int[]{1, 2, 3}, null, 3L);
        repository.add(entity);

        Optional<ArrayEntity> found = repository.findById(3L);

        assertTrue(found.isPresent());
        assertEquals(3L, found.get().getId());
    }

    @Test
    @DisplayName("Should return empty Optional for non-existent id")
    void shouldReturnEmptyOptionalForNonExistentId() {
        Optional<ArrayEntity> found = repository.findById(888L);

        assertFalse(found.isPresent());
    }

    @Test
    @DisplayName("Should find all by sum greater than")
    void shouldFindAllBySumGreaterThan() throws ArrayException {
        ArrayEntity entity1 = new ArrayEntity(new int[]{1, 2, 3}, null, 10L);
        ArrayEntity entity2 = new ArrayEntity(new int[]{10, 20, 30}, null, 11L);

        repository.add(entity1);
        repository.add(entity2);

        warehouse.saveMetrics(10L, new ArrayMetrics(10L, 6, 1, 3, 2.0));
        warehouse.saveMetrics(11L, new ArrayMetrics(11L, 60, 10, 30, 20.0));

        List<ArrayEntity> result = repository.findAllBySumGreaterThan(10);

        assertEquals(1, result.size());
        assertEquals(11L, result.get(0).getId());
    }

    @Test
    @DisplayName("Should get sorted by id")
    void shouldGetSortedById() throws ArrayException {
        ArrayEntity entity1 = new ArrayEntity(new int[]{1}, null, 30L);
        ArrayEntity entity2 = new ArrayEntity(new int[]{2}, null, 10L);
        ArrayEntity entity3 = new ArrayEntity(new int[]{3}, null, 20L);

        repository.add(entity1);
        repository.add(entity2);
        repository.add(entity3);

        List<ArrayEntity> sorted = repository.getSortedById();

        assertEquals(10L, sorted.get(0).getId());
        assertEquals(20L, sorted.get(1).getId());
        assertEquals(30L, sorted.get(2).getId());
    }

    @Test
    @DisplayName("Should get sorted by length")
    void shouldGetSortedByLength() throws ArrayException {
        ArrayEntity entity1 = new ArrayEntity(new int[]{1, 2, 3}, null, 40L);
        ArrayEntity entity2 = new ArrayEntity(new int[]{1}, null, 41L);
        ArrayEntity entity3 = new ArrayEntity(new int[]{1, 2}, null, 42L);

        repository.add(entity1);
        repository.add(entity2);
        repository.add(entity3);

        List<ArrayEntity> sorted = repository.getSortedByLength();

        assertEquals(1, sorted.get(0).getLength());
        assertEquals(2, sorted.get(1).getLength());
        assertEquals(3, sorted.get(2).getLength());
    }
}