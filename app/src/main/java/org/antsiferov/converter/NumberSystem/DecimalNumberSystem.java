package org.antsiferov.converter.NumberSystem;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.ContentValues.TAG;

/**
 * Created by Бабайка on 24.09.2016.
 */

public class DecimalNumberSystem extends NumberSystem {
    private static String staticRegExp = "[0-9]+([.]?[0-9]+)?";

    static public boolean checkingForComplianceWithANumberSystem(String number) {
        if (!number.isEmpty()) {
            Pattern pattern = Pattern.compile(staticRegExp);
            Matcher matcher = pattern.matcher(number);
            if (!matcher.matches()) {
                return false;
            }
        } else {
            return false;
        }

        return true;
    }

    public DecimalNumberSystem(String num_str) {
        super(num_str);
        super.numberSystem = "9";
    }

    public DecimalNumberSystem() {
        super("");
        super.numberSystem = "9";
    }
}