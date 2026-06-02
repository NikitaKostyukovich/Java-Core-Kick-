package service.implement.arraystatsimpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.arraystats.ArrayStats;
import exception.ArrayException;

import java.util.Optional;

public class ArrayStatsImpl implements ArrayStats {

    private static final Logger logger = LogManager.getLogger(ArrayStatsImpl.class);

    @Override
    public Optional<Integer> findMax(int[] array) {
        logger.info("Method findMax execution started");

        if (array == null) {
            logger.error("Error in findMax: provided array is null");
            throw new ArrayException("Array cannot be null for calculating statistics.");
        }

        if (array.length == 0) {
            logger.warn("Array is empty, calculation of max value is impossible");
            return Optional.empty();
        }

        try {
            int maxValue = array[0];
            for (int i = 1; i < array.length; i++) {
                if (array[i] > maxValue) {
                    maxValue = array[i];
                }
            }
            logger.info("Method findMax successfully finished. Max value: {}", maxValue);
            return Optional.of(maxValue);

        } catch (Throwable t) {
            logger.error("Unexpected error occurred while finding max value", t);
            throw new ArrayException("Critical failure during max value calculation", t);
        }
    }

    @Override
    public Optional<Integer> findMin(int[] array) {
        logger.info("Method findMin execution started");

        if (array == null) {
            logger.error("Error in findMin: provided array is null");
            throw new ArrayException("Array cannot be null for calculating statistics.");
        }

        if (array.length == 0) {
            logger.warn("Array is empty, calculation of min value is impossible");
            return Optional.empty();
        }

        try {
            int minValue = array[0];
            for (int i = 1; i < array.length; i++) {
                if (array[i] < minValue) {
                    minValue = array[i];
                }
            }
            logger.info("Method findMin successfully finished. Min value: {}", minValue);
            return Optional.of(minValue);

        } catch (Throwable t) {
            logger.error("Unexpected error occurred while finding min value", t);
            throw new ArrayException("Critical failure during min value calculation", t);
        }
    }

    @Override
    public Optional<Double> findAverage(int[] array) {
        logger.info("Method findAverage execution started");

        if (array == null) {
            logger.error("Error in findAverage: provided array is null");
            throw new ArrayException("Array cannot be null for calculating statistics.");
        }

        if (array.length == 0) {
            logger.warn("Array is empty, calculation of average value is impossible");
            return Optional.empty();
        }

        try {
            int sum = 0;
            for (int i = 0; i < array.length; i++) {
                sum += array[i];
            }

            double avgResult = (double) sum / array.length;
            logger.info("Method findAverage successfully finished. Average value: {}", avgResult);
            return Optional.of(avgResult);

        } catch (Throwable t) {
            logger.error("Unexpected error occurred while finding average value", t);
            throw new ArrayException("Critical failure during average value calculation", t);
        }
    }

    @Override
    public Optional<Integer> findSum(int[] array) {
        logger.info("Method findSum execution started");

        if (array == null) {
            logger.error("Error in findSum: provided array is null");
            throw new ArrayException("Array cannot be null for calculating statistics.");
        }

        if (array.length == 0) {
            logger.warn("Array is empty, calculation of sum is impossible");
            return Optional.empty();
        }

        try {
            int arraySum = 0;
            for (int i = 0; i < array.length; i++) {
                arraySum += array[i];
            }
            logger.info("Method findSum successfully finished. Total sum: {}", arraySum);
            return Optional.of(arraySum);

        } catch (Throwable t) {
            logger.error("Unexpected error occurred while calculating sum", t);
            throw new ArrayException("Critical failure during sum calculation", t);
        }
    }
}