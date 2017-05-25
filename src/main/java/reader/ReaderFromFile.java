package reader;

import entity.Point;
import entity.Sphere;
import entity.SphereParameters;
import exception.IncorrectDataTypeException;
import exception.MissingDataException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import storage.SphereStorage;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vbokh on 24.05.2017.
 */
public class ReaderFromFile {
    public static final Logger LOGGER = LogManager.getLogger();

    public Map<Point, Double> readDataFromFile(String fileName){
        Map<Point, Double> dataFromFile = new HashMap<Point, Double>();
        BufferedReader reader = null;
        try {
            reader = getBufferedReader(fileName);
            String line = "";
            while ((line = reader.readLine()) != null) {
                try {
                    String[] data;
                    try {
                        data = line.split(" ");
                        double x = Double.parseDouble(data[0]);
                        double y = Double.parseDouble(data[1]);
                        double z = Double.parseDouble(data[2]);
                        double radius = Double.parseDouble(data[3]);
                        Point point = new Point(x, y, z);
                        dataFromFile.put(point, radius);
                    } catch (NumberFormatException e) {
                        throw new IncorrectDataTypeException(e);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new MissingDataException(e);
                    }
                } catch (IncorrectDataTypeException e) {
                    LOGGER.log(Level.ERROR, "Reading error. Incorrect data. Expected double ");
                } catch (MissingDataException e) {
                    LOGGER.log(Level.ERROR, "Reading error. Not enough data ");
                }
            }
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.ERROR, "There is no such file. " + e.getMessage());
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, " Error with reader. " + e.getMessage());
        }finally {
            try {
                reader.close();
            } catch (IOException e) {
                LOGGER.log(Level.ERROR, " Error while closing reader. " + e.getMessage());
            }
        }
        return dataFromFile;
    }

    private BufferedReader getBufferedReader(String fileName) throws FileNotFoundException {
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(new File(fileName)));
        return reader;
    }


}
