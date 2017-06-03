package com.bokhan.sphere.service;

import com.bokhan.sphere.exception.MissingDataException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by vbokh on 24.05.2017.
 */
public class SphereValidator {
    private static String REGEX = "\\s*(\\-?([0-9]||[0-9]+\\.[0-9]+)\\s+){3}([0-9]||[0-9]+\\.[0-9]+)\\s*";

    public List<String> validateData(List<String> unvalidatedData) throws MissingDataException {
        if (unvalidatedData == null || unvalidatedData.size() == 0) {
            throw new MissingDataException("No data was received to validate");
        }
        ArrayList<String> validatedData = new ArrayList<>();
        Pattern pattern = Pattern.compile(REGEX);
        for (String validatingLine : unvalidatedData) {
            if (pattern.matcher(validatingLine).matches()) {
                validatedData.add(validatingLine);
            }
        }
        return validatedData;
    }
}

