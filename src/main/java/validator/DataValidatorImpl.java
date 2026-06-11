package validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class DataValidatorImpl implements DataValidator{
    private static final Logger logger = LogManager.getLogger(DataValidatorImpl.class);
    private static final String REGEX_VALID_LINE = "^-?\\d+(\\s*[,;-]?\\s*-?\\d+)*$";
    @Override
    public boolean isValid(String line) {
        if (line == null || line.trim().isEmpty()) {
            logger.warn("Validation has didnt passed: string is empty or null");
            return false;
        }
        boolean isValid = line.trim().matches(REGEX_VALID_LINE);
        if (!isValid) {
            logger.warn("Validation has didnt passed, string has unacceptable: {}", line);
        }
        return isValid;
    }
}