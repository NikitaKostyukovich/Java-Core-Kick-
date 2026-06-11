package datareader;



import exception.ArrayException;

import java.util.List;

public interface DataReaderService {
    List<String> readData(String pathtofile) throws ArrayException;
}
