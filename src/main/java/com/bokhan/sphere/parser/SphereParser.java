package com.bokhan.sphere.parser;

import com.bokhan.sphere.entity.Point;;
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

    public Map<Point, Double> parseData(List<String> unparsedData) throws MissingDataException {
        if (unparsedData == null || unparsedData.size() == 0) {
            throw new MissingDataException("No data was received for parsing");
        }
        Map<Point, Double> sphereValues = new HashMap<>();
        for (String parsingString : unparsedData) {
            List<String> dataForSphereValues = new ArrayList<String>(Arrays.asList(parsingString.split(DELIMITER)));
            try {
                if (dataForSphereValues.size() > 4) {
                    throw new RedunduntDataException("Extra values found. Don't know what to do...");
                }
                Point point = createPoint(dataForSphereValues);
                double radius = Double.parseDouble(dataForSphereValues.get(3));
                sphereValues.put(point, radius);
            } catch (NumberFormatException e) {
                LOGGER.log(Level.ERROR, "Parsing error. Incorrect data. Expected double value " + e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                LOGGER.log(Level.ERROR, "Parsing error. Not enough data for creating values " + e.getMessage());
            } catch (RedunduntDataException e) {
                LOGGER.log(Level.ERROR, e.getMessage());
            }
        }
        return sphereValues;
    }

    private Point createPoint(List<String> data) {
        double x = Double.parseDouble(data.get(0));
        double y = Double.parseDouble(data.get(1));
        double z = Double.parseDouble(data.get(2));
        return new Point(x, y, z);
    }
}
