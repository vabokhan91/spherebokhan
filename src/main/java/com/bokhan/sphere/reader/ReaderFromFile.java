package com.bokhan.sphere.reader;

import com.bokhan.sphere.entity.Point;
import com.bokhan.sphere.entity.Sphere;
import com.bokhan.sphere.entity.SphereParameters;
import com.bokhan.sphere.exception.IncorrectDataTypeException;
import com.bokhan.sphere.exception.MissingDataException;
import com.bokhan.sphere.exception.NoFileException;
import com.bokhan.sphere.storage.SphereStorage;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by vbokh on 24.05.2017.
 */
public class ReaderFromFile {
    public static final Logger LOGGER = LogManager.getLogger();

    public List<String> readDataFromFile(String fileName) throws NoFileException{

        List<String> lines = null;
        if (fileName == null) {
            throw new NoFileException();
        }
        try {
            lines = Files.lines(new File(fileName).toPath()).map(s -> s.trim()).filter(s -> !s.isEmpty()).collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.ERROR, "There is no such file. " + e.getMessage());
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, " Error with reader. " + e.getMessage());
        }
        return lines;
    }

    private BufferedReader getBufferedReader(String fileName) throws FileNotFoundException {
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(new File(fileName)));
        return reader;
    }


}
