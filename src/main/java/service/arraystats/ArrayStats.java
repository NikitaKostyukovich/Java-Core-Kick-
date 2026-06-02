package service.arraystats;

import java.util.Optional;

public interface ArrayStats {
    Optional<Integer> findMax(int[] array);
    Optional<Integer> findMin(int[] array);
    Optional<Double> findAverage(int[] array);
    Optional<Integer> findSum(int[] array);
}
