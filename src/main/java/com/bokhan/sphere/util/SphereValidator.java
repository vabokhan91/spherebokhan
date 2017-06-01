package com.bokhan.sphere.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by vbokh on 24.05.2017.
 */
public class SphereValidator {
    private static String REGEX = "\\s*(\\-?([0-9]||[0-9]+\\.[0-9]+)\\s+){3}([0-9]||[0-9]+\\.[0-9]+)\\s*";

    public List<String> validateData(List<String> unvalidatedData) {
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

