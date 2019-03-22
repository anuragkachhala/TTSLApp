package com.software.ttsl.Utils;

public class EmployeeMethodUtils {

    public static String calculatePercentage(int value, int percentage){


        int percentageValue = (int)(value*(percentage/100.0f));
        return String.valueOf(percentageValue);
    }
}
