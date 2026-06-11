package parser;

import exception.ArrayException;

public interface DataParser {
    public int[] dataParse(String line) throws ArrayException;
}
