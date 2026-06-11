package repository;

import entity.ArrayEntity;

import java.util.List;
import java.util.Optional;

public interface ArrayRepository {
    void add(ArrayEntity entity);

    boolean removeById(long id);
    void clear();
    Optional<ArrayEntity> findById(long id);

    List<ArrayEntity> findAllBySumGreaterThan(int targetValue);

    List<ArrayEntity> findAllByAverageLessThan(double targetValue);

    List<ArrayEntity> findAllByMinEqualTo(int targetValue);

    List<ArrayEntity> findAllByCountGreaterThan(int targetValue);

    List<ArrayEntity> getSortedById();

    List<ArrayEntity> getSortedByLength();

    List<ArrayEntity> getSortedByFirstElement();

}
