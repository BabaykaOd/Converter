package org.antsiferov.converter.NumberSystem;

import android.util.Log;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Бабайка on 24.09.2016.
 */

public class OctalNumberSystem extends NumberSystem {
    private static String staticRegExp = "[0-7]+([.]?[0-7]+)?";

    public OctalNumberSystem(String num_str) {
        super(num_str);
        super.numberSystem = "8";
    }

    public OctalNumberSystem() {
        super("");
        super.numberSystem = "8";
    }

    public BinaryNumberSystem toBinary() {
        Log.d("octal", "number = " + number);
        DecimalNumberSystem d = new DecimalNumberSystem(this.toDecimal().toString());
        return new BinaryNumberSystem(d.toBinary().toString());
    }

    public DecimalNumberSystem toDecimal() {
        int decimalNumber = toDecimalIntPart();
        double decimalDoublePart = toDecimalDoublePart();

        if (decimalDoublePart != 0) {
            return new DecimalNumberSystem((decimalDoublePart + decimalNumber) + "");
        } else {
            return new DecimalNumberSystem(decimalNumber + "");
        }
    }

    public HexadecimalNumberSystem toHexadecimal() {
        DecimalNumberSystem dec = new DecimalNumberSystem(this.toDecimal().toString());
        return dec.toHexadecimal();
    }

    private int toDecimalIntPart() {
        int temp = 0;
        String intPartOctalNumber = getIntPart();
        int size = intPartOctalNumber.length() - 1;

        for (int i = size; i > -1; i--) {
            temp += (Math.pow(Integer.parseInt(numberSystem), i) * Integer.parseInt(intPartOctalNumber.charAt(size - i) + ""));
        }

        return temp;
    }

    private double toDecimalDoublePart() {
        String doublePart = getDoublePart();
        double decNum = 0.0;
        int size = doublePart.length();

        for (int i = 0; i < size; i++) {
            decNum += (Math.pow(8, (i + 1) * -1) * Integer.parseInt(doublePart.charAt(i) + ""));
        }

        return decNum;
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
}
