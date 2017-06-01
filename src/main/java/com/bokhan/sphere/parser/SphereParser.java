package com.bokhan.sphere.parser;

import com.bokhan.sphere.entity.Point;
import com.bokhan.sphere.exception.IncorrectDataTypeException;
import com.bokhan.sphere.exception.MissingDataException;
import com.bokhan.sphere.exception.RedunduntDataException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.*;

/**
 * Created by vbokh on 28.05.2017.
 */
public class SphereParser {
    private final static Logger LOGGER = LogManager.getLogger();
    private final static String DELIMITER = "\\s+";

    public Map<Point, Double> parseListOfData(List<String> lines) {
        Map<Point, Double> sphereValues = new HashMap<>();
        for (String line : lines) {
            List<String> dataForSphereValues = new ArrayList<String>(Arrays.asList(line.split(DELIMITER)));
            try {
                if (dataForSphereValues.size() > 4) {
                    throw new RedunduntDataException("Extra parameters found. Don't know what to do...");
                }
                try{
                    Point point = createPoint(dataForSphereValues);
                    double radius = Double.parseDouble(dataForSphereValues.get(3));
                    sphereValues.put(point, radius);
                } catch (NumberFormatException e) {
                    throw new IncorrectDataTypeException("Parsing error. Incorrect data. Expected double value.", e);
                } catch (IndexOutOfBoundsException e) {
                    throw new MissingDataException("Parsing error. Not enough data for creating values.", e);
                }
            } catch (IncorrectDataTypeException e) {
                LOGGER.log(Level.ERROR, e.getMessage());
            } catch (MissingDataException e) {
                LOGGER.log(Level.ERROR, e.getMessage());
            }catch (RedunduntDataException e) {
                LOGGER.log(Level.ERROR, e.getMessage());
            }
        }
        return sphereValues;
    }

    private Point createPoint(List<String> data){
        double x = Double.parseDouble(data.get(0));
        double y = Double.parseDouble(data.get(1));
        double z = Double.parseDouble(data.get(2));
        return new Point(x, y, z);
    }
}
