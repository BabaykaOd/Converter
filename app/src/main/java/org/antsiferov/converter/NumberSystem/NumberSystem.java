package org.antsiferov.converter.NumberSystem;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.ContentValues.TAG;

/**
 * Created by Бабайка on 01.11.2016.
 */

public abstract class NumberSystem {
    protected String number = "";
    protected String numberSystem = "";
    protected String regExp;
    public static final String hexadecimalNumbers = "0123456789ABCDEF";

    public NumberSystem(String number) {
        this.number = number;
    }

    public NumberSystem() {
        this.number = "";
    }

    public boolean checkingForComplianceWithANumberSystem() {
        if (!number.isEmpty()) {
            regExp = "[0-" + (Integer.parseInt(numberSystem) - 1) + "]+([.]?[0-" + (Integer.parseInt(numberSystem) - 1) + "]+)?";
            Pattern pattern = Pattern.compile(regExp);
            Matcher matcher = pattern.matcher(number);
            if (!matcher.matches()) {
                return false;
            }
        } else {
            return false;
        }

        return true;
    }

    protected String getIntPart() {
        String intPart = "";
        int lengNumber = number.length();

        for (int i = 0;i < lengNumber ;i++) {
            if (number.charAt(i) != '.') {
                intPart += number.charAt(i);
            } else {
                break;
            }
        }

        return intPart;
    }

    protected String getDoublePart() {
        String doublePart = "";
        int lengNumber = number.length();

        for (int i = 0; i < lengNumber; i++) {
            if (number.charAt(i) == '.') {
                doublePart = number.substring(i + 1, lengNumber);
            }
        }

        return doublePart;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    protected String reversion(String str) {
        String tmp = "";
        for (int i = str.length() - 1; i > -1; i--) {
            tmp += str.charAt(i);
        }
        return tmp;
    }

    public String toString() {
        return number;
    }
}
