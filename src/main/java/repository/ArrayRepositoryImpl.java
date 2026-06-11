package repository;
import entity.ArrayEntity;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import exception.ArrayException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import warehouse.ArrayMetrics;
import warehouse.Warehouse;

public class ArrayRepositoryImpl implements ArrayRepository{
    private final static Logger logger = LogManager.getLogger(ArrayRepositoryImpl.class);
    private static ArrayRepositoryImpl instance;
    private Warehouse warehouse;
    private List<ArrayEntity> arrays;
    private ArrayRepositoryImpl(){
        this.arrays = new ArrayList<>();
        this.warehouse = Warehouse.getInstance();
    }
    public static ArrayRepositoryImpl getInstance(){
        if(instance == null){
            instance = new ArrayRepositoryImpl();
        }
        return instance;
    }
    @Override
    public void clear(){
        this.arrays.clear();
    }
   @Override
    public void add(ArrayEntity entity){
        arrays.add(entity);
        logger.info("Object was added successfully");
    }
    @Override
    public boolean removeById(long id){

      if(arrays.removeIf(entity -> entity.getId() == id)) {
          logger.info("Object was deleted successfully");
          return true;

      }else {
          logger.error("Object with such Id not found");
          return false;
      }
    }
    @Override
    public Optional<ArrayEntity> findById(long id){
            return arrays.stream().filter(entity -> entity.getId() == id).findFirst();
    }
    @Override
    public List<ArrayEntity> findAllBySumGreaterThan(int targetValue){
        List<ArrayEntity> listOfSumGreaterThan = new ArrayList<>();
        for(ArrayEntity entity : arrays){
            long entityID = entity.getId();
            ArrayMetrics metrics = warehouse.getMetrics(entityID);
            if(metrics != null) {
                if (metrics.getSum() > targetValue){
                    listOfSumGreaterThan.add(entity);
                }
            }
        }
        return listOfSumGreaterThan;
    }
    @Override
    public List<ArrayEntity> findAllByAverageLessThan(double targetValue){
        List<ArrayEntity> listOfAverageLessThan = new ArrayList<>();
        for(ArrayEntity e : arrays){
            long entityID = e.getId();
            ArrayMetrics metrics = warehouse.getMetrics(entityID);
            if(metrics != null && metrics.getAverage() < targetValue){
                listOfAverageLessThan.add(e);
            }
        }
            return listOfAverageLessThan;

    }
    @Override
    public List<ArrayEntity> findAllByMinEqualTo(int targetValue){
        List<ArrayEntity> resultList = new ArrayList<>();
        for (ArrayEntity entity : arrays) {
            ArrayMetrics metrics = warehouse.getMetrics(entity.getId());
            if (metrics != null && metrics.getMin() == targetValue) {
                resultList.add(entity);
            }
        }
        return resultList;
    }
    @Override
    public List<ArrayEntity> findAllByCountGreaterThan(int targetValue){
        List<ArrayEntity> resultList = new ArrayList<>();
        for (ArrayEntity entity : arrays) {
            if (entity.getLength() > targetValue) {
                resultList.add(entity);
            }
        }
        return resultList;
    }
    @Override
    public List<ArrayEntity> getSortedById(){
        List<ArrayEntity> sortedByID = new ArrayList<>(this.arrays);
        Comparator<ArrayEntity> idComparator = Comparator.comparingLong(ArrayEntity::getId);
        sortedByID.sort(idComparator);
        return sortedByID;
    }
    @Override
    public List<ArrayEntity> getSortedByLength(){
        List<ArrayEntity> sortedListByLength = new ArrayList<>(this.arrays);
        Comparator<ArrayEntity> lengthComparator = Comparator.comparingInt(ArrayEntity::getLength);
        sortedListByLength.sort(lengthComparator);
        return sortedListByLength;
    }
    @Override
    public List<ArrayEntity> getSortedByFirstElement(){
        List<ArrayEntity> sortedListByFisrtElement = new ArrayList<>(this.arrays);
        Comparator<ArrayEntity> fistElementComparator = (entity1, entity2) -> {
            int length1 = entity1.getLength();
            int length2 = entity2.getLength();
            if(length1 == 0 && length2 == 0){
                return 0;
            }
            if (length1 == 0) {
                return -1;
            }
            if (length2 == 0) {
                return 1;
            }
                int firstElement1 = entity1.getArray()[0];
                int firstElement2 = entity2.getArray()[0];
            return Integer.compare(firstElement1, firstElement2);
        };

         sortedListByFisrtElement.sort(fistElementComparator);
        return sortedListByFisrtElement;
    }
}
