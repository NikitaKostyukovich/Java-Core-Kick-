package parser;
import exception.ArrayException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class DataParserImpl implements DataParser{
    private static final String SPLIT_REGEX = "[ ,;]+";
    private static  final Logger logger = LogManager.getLogger(DataParserImpl.class);
    @Override
    public int[] dataParse(String line) throws ArrayException{
        logger.debug("Attempting to parse line: {}", line);
        if (line == null || line.isBlank()) {
            logger.error("Received null or blank line for parsing");
            throw new ArrayException("Line to parse cannot be null or blank");
        }
        String[] elems = line.split(SPLIT_REGEX);
        int[] numbers = new int[elems.length];
        for(int i = 0; i < elems.length; i++){
            try {
                numbers[i] = Integer.parseInt(elems[i]);
            }catch(NumberFormatException e){
                logger.error("Error while parsing"+elems[i]);
                throw new ArrayException("Failed to parse integer from string: " + elems[i], e);
            }
        }
        logger.info("Successfully parsed line into array of size {}", numbers.length);
        return numbers;
    }

}
