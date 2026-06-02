package entity;
import exception.ArrayException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.*;
public class ArrayEntity {
    private final int[] copiedArray;
    private final static Logger logger = LogManager.getLogger(ArrayEntity.class);
    public ArrayEntity(int[] data){
       if(data != null) {
           
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
}
