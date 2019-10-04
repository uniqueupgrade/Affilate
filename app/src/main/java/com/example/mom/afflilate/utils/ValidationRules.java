package com.example.mom.afflilate.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationRules {
    private static final String EMAIL_STRING = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static boolean isValidMail(String email) {
        boolean check;
        Pattern p;
        Matcher m;
        p = Pattern.compile(EMAIL_STRING);
        m = p.matcher(email);
        check = m.matches();

        return check;
    }

    public static boolean isValidIndianMobile(String phone) {
        if (phone.length() == 10) {
            return true;
        }else {
            return false;
        }
    }
}
