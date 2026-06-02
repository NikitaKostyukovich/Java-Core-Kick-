package factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import entity.ArrayEntity;

public class EntityFactoryBuilding {
    private final static Logger logger = LogManager.getLogger(EntityFactoryBuilding.class);
    public ArrayEntity build(int[] file_data) {
        logger.info("Object was created successfully");
        return new ArrayEntity(file_data);

    }
}
