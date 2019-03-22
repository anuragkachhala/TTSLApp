package com.software.ttsl.Utils;

import android.util.Patterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeValidationUtil {

    public static Pattern pattern;


    public static boolean validateEmail(String emailID) {

        pattern = Pattern.compile(EmployConstantUtil.EMAIL_VALIDATION_REGEX);

        return pattern.matcher(emailID).matches();

    }


    public static boolean validateMobile(String phone) {

        if (!Pattern.matches(EmployConstantUtil.PHONE_VALIDATION_REGEX, phone)) {
            if (phone.length() < 10 || phone.length() > 13) {
                // if(phone.length() != 10) {
                return false;

            } else {
                return true;
            }
        } else {
            return false;
        }


    }

    public static boolean validateName(String name,int min) {

        if (name.length() < min) {
            // if(phone.length() != 10) {
            return false;

        } else {
            return true;
        }

    }

    public static boolean validateFax(String fax) {
        if (!Pattern.matches(EmployConstantUtil.PHONE_VALIDATION_REGEX, fax)) {
            if (fax.length() < 7 || fax.length() > 13) {
                // if(phone.length() != 10) {
                return false;

            } else {
                return true;
            }
        } else {
            return false;
        }

    }

    public static boolean validateUrl(String url) {
        pattern = Pattern.compile(EmployConstantUtil.WEB_URL);
        return pattern.matcher(url.toLowerCase()).matches();
    }


    public static boolean validateUserName(String skypeId) {
        pattern = Pattern.compile(EmployConstantUtil.USERNAME_REGEX);
        return pattern.matcher(skypeId).matches();

    }





}
