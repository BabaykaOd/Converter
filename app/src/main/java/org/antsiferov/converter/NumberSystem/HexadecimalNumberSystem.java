package org.antsiferov.converter.NumberSystem;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Бабайка on 24.09.2016.
 */

public class HexadecimalNumberSystem extends NumberSystem {
    static private String staticRegExp = "[0-9A-F]+([.]?[0-9A-F]+)?";

    public HexadecimalNumberSystem(String num_str) {
        super(num_str);
        super.numberSystem = "15";
    }

    public HexadecimalNumberSystem() {
        super("");
        super.numberSystem = "15";
    }

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

    public boolean checkingForComplianceWithANumberSystem() {
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

    @Override
    public BinaryNumberSystem toBinary() {
        DecimalNumberSystem dec = new DecimalNumberSystem(this.toDecimal().toString());
        return dec.toBinary();
    }

    @Override
    public OctalNumberSystem toOctal() {
        DecimalNumberSystem dec = new DecimalNumberSystem(this.toDecimal().toString());
        return dec.toOctal();
    }

    @Override
    public DecimalNumberSystem toDecimal() {
        double temp = 0.0;
        int number = 0;
        int size = this.number.length() - 1;


        for (int i = size; i > -1; i--) {
            for (int j = 0; j < hexadecimalNumbers.length(); j++) {
                if (hexadecimalNumbers.charAt(j) == this.number.charAt(size - i)) {
                    number = j;
                }
            }

            temp += (Math.pow(16, i) * number);
        }

        return new DecimalNumberSystem(Integer.toString((int)temp));
    }
}
