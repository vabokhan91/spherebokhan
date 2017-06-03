package com.bokhan.sphere.reader;

import com.bokhan.sphere.exception.NoFileException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by vbokh on 24.05.2017.
 */
public class ReaderFromFile {
    private final static Logger LOGGER = LogManager.getLogger();

    public List<String> readDataFromFile(String fileName) throws NoFileException {
        if (fileName == null || fileName.length() == 0) {
            throw new NoFileException(String.format("File %s not found", fileName));
        }
        List<String> dataFromFile = null;
        try {
            dataFromFile = Files.lines(new File(fileName).toPath()).map(s -> s.trim()).filter(s -> !s.isEmpty()).collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, "Error with file. " + e.getMessage());
        }
        return dataFromFile;
    }
}
