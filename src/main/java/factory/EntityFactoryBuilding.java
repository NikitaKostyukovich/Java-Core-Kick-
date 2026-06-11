package factory;

import exception.ArrayException;
import observer.ArrayObserverImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import entity.ArrayEntity;


public class EntityFactoryBuilding {
    private long nextid = 0;
    private ArrayObserverImpl observer;
    private final static Logger logger = LogManager.getLogger(EntityFactoryBuilding.class);
    public ArrayEntity build(int[] file_data, ArrayObserverImpl observer) throws ArrayException {
        long id = this.nextid++;
        logger.info("Object was created successfully");
        return new ArrayEntity(file_data, observer, id);
    }
}
