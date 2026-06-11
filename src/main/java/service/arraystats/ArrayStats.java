package service.arraystats;

import exception.ArrayException;

import java.util.Optional;

public interface ArrayStats {
    Optional<Integer> findMax(int[] array) throws ArrayException;
    Optional<Integer> findMin(int[] array) throws ArrayException;
    Optional<Double> findAverage(int[] array) throws ArrayException;
    Optional<Integer> findSum(int[] array) throws ArrayException;
}
