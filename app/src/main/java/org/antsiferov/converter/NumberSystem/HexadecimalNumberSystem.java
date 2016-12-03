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
        super.numberSystem = "16";
    }

    public HexadecimalNumberSystem() {
        super("");
        super.numberSystem = "16";
    }

    public BinaryNumberSystem toBinary() {
        DecimalNumberSystem dec = new DecimalNumberSystem(this.toDecimal().toString());
        return dec.toBinary();
    }

    public OctalNumberSystem toOctal() {
        DecimalNumberSystem dec = new DecimalNumberSystem(this.toDecimal().toString());
        return dec.toOctal();
    }

    public DecimalNumberSystem toDecimal() {
        int decimalNumber = toDecimalIntPart();
        double doublePartDecNumber = toDecimalDoublePart();

        if (doublePartDecNumber != 0) {
            return new DecimalNumberSystem((doublePartDecNumber + decimalNumber) + "");
        } else {
            return new DecimalNumberSystem(decimalNumber + "");
        }
    }

    private double toDecimalDoublePart() {
        String doublePartHexNumber = getDoublePart();
        int number = 0;
        int size = doublePartHexNumber.length();
        double doublePartDecimalNumber = 0.0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < hexadecimalNumbers.length(); j++) {
                if (hexadecimalNumbers.charAt(j) == doublePartHexNumber.charAt(i)) {
                    number = j;
                }
            }

            doublePartDecimalNumber += (Math.pow(16, (i + 1) * (-1)) * number);
        }

        return doublePartDecimalNumber;
    }

    private int toDecimalIntPart() {
        int temp = 0;
        int number = 0;
        String intPartHexNumber = getIntPart();
        int size = intPartHexNumber.length() - 1;

        for (int i = size; i > -1; i--) {
            for (int j = 0; j < hexadecimalNumbers.length(); j++) {
                if (hexadecimalNumbers.charAt(j) == this.number.charAt(size - i)) {
                    number = j;
                }
            }

            temp += (Math.pow(Integer.parseInt(numberSystem), i) * number);
        }

        return temp;
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
}
