package observer;

import exception.ArrayException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import entity.ArrayEntity;
import repository.ArrayRepositoryImpl;
import service.implement.arraystatsimpl.ArrayStatsImpl;
import validator.DataValidatorImpl;
import warehouse.ArrayMetrics;
import warehouse.Warehouse;

import java.util.Optional;

public class ArrayObserverImpl implements ArrayObserver {
    private ArrayRepositoryImpl repository;
    private ArrayStatsImpl serviceStats;
    private Warehouse warehouse;
    private static final Logger logger = LogManager.getLogger(ArrayObserverImpl.class);

    public ArrayObserverImpl(ArrayRepositoryImpl repository, ArrayStatsImpl serviceStats, Warehouse warehouse){
        this.repository = repository;
        this.serviceStats = serviceStats;
        this.warehouse = warehouse;
    }
    @Override
    public void update(long id) {
       Optional<ArrayEntity> foundedEntity = this.repository.findById(id);
       if(foundedEntity.isPresent()){
           ArrayEntity arrayObject = foundedEntity.get();
           int[] arrayOfInt = arrayObject.getArray();
           if (arrayOfInt.length == 0) {
               logger.warn("Cannot calculate metrics for empty array. ID: {}", id);
               return;
           }

            Optional<Integer> sumOptional = this.serviceStats.findSum(arrayOfInt);
            Optional<Integer> maxValueOptional = this.serviceStats.findMax(arrayOfInt);
            Optional<Integer> minValueOptional = this.serviceStats.findMin(arrayOfInt);
            Optional<Double> averageOptional = this.serviceStats.findAverage(arrayOfInt);

           if (sumOptional.isPresent() && minValueOptional.isPresent() && maxValueOptional.isPresent() && averageOptional.isPresent()) {
               int sum = sumOptional.get();
               int min = minValueOptional.get();
               int max = maxValueOptional.get();
               double average = averageOptional.get();

               ArrayMetrics metrics = new ArrayMetrics(id, sum, min, max, average);
               this.warehouse.saveMetrics(id, metrics);
           } else {
               logger.error("Failed to calculate metrics for array ID: {}", id);
           }
       }


    }
}
