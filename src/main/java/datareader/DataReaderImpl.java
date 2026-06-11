package datareader;

import exception.ArrayException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DataReaderImpl implements DataReaderService{
    private final static Logger logger = LogManager.getLogger(DataReaderImpl.class);
    @Override
    public List<String> readData(String pathtofile) throws ArrayException{
        try{
            Path path = Path.of(pathtofile);
            return Files.readAllLines(path);

        }
        catch (IOException ex){
            logger.error("Error while reading file" + pathtofile);
            throw new ArrayException("Error while reading file", ex);
        }

    }

}
