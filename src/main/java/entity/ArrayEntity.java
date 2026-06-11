package entity;
import exception.ArrayException;
import observer.ArrayObserverImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repository.ArrayRepositoryImpl;

import java.util.*;
public class    ArrayEntity {
    private final int[] copiedArray;
    private long id;
    private final ArrayObserverImpl observer;
    private final static Logger logger = LogManager.getLogger(ArrayEntity.class);
    public ArrayEntity(int[] data, ArrayObserverImpl observer, long id) throws ArrayException{
       if(data != null) {
           this.id = id;
           this.observer = observer;
           this.copiedArray = Arrays.copyOf(data, data.length);
       }
        else{
            logger.error("Input array is null");
            throw new ArrayException("Input array cannot be null");
       }

    }


    public int[] getArray(){

        return Arrays.copyOf(copiedArray, copiedArray.length);
    }

    public long getId(){
        return id;
    }

    public int getElement(int index) throws ArrayException {
        checkIndex(index);
        return copiedArray[index];
    }


    public void setElement(int index, int value) throws ArrayException {
        checkIndex(index);
        copiedArray[index] = value;
        logger.info("Element at index {} changed to {}. ID: {}", index, value, id);

        if(observer != null){
            observer.update(this.id);
        }
    }

    public int getLength() {
        return copiedArray.length;
    }

    private void checkIndex(int index) throws ArrayException {
        if (index < 0 || index >= copiedArray.length) {
            logger.error("Index is out of bounds for array length");
            throw new ArrayException("Index out of bounds: " + index);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayEntity that = (ArrayEntity) o;
        return id == that.id && Arrays.equals(copiedArray, that.copiedArray);
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(id);
        result = 31 * result + Arrays.hashCode(copiedArray);
        return result;
    }

    @Override
    public String toString() {
        return "ArrayEntity{" +
                "id=" + id +
                ", array=" + Arrays.toString(copiedArray) +
                '}';
    }
}
