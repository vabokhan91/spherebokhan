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
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String DELIMITER = "\\s+";

    public Map<Point, Double> parseDataFromReader(List<String> lines) {
        Map<Point, Double> sphereParams = new HashMap<>();
        for (String line : lines) {
            List<String> parameters = new ArrayList<String>(Arrays.asList(line.split(DELIMITER)));
            try {
                if (parameters.size() > 4) {
                    throw new RedunduntDataException("Extra parameters found. Don't know what to do...");
                }
                try{
                    Point point = createPoint(parameters);
                    double radius = Double.parseDouble(parameters.get(3));
                    sphereParams.put(point, radius);
                } catch (NumberFormatException e) {
                    throw new IncorrectDataTypeException(e);
                } catch (IndexOutOfBoundsException e) {
                    throw new MissingDataException(e);
                }
            } catch (IncorrectDataTypeException e) {
                LOGGER.log(Level.ERROR, "Reading error. Incorrect data. Expected double.");
            } catch (MissingDataException e) {
                LOGGER.log(Level.ERROR, "Reading error. Not enough data.");
            }catch (RedunduntDataException e) {
                LOGGER.log(Level.ERROR, e.getMessage());
            }
        }
        return sphereParams;
    }

    private Point createPoint(List<String> data) throws NumberFormatException,IndexOutOfBoundsException{
        double x = Double.parseDouble(data.get(0));
        double y = Double.parseDouble(data.get(1));
        double z = Double.parseDouble(data.get(2));
        return new Point(x, y, z);
    }
}
