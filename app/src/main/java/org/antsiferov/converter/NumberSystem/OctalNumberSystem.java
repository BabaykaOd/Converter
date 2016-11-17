package org.antsiferov.converter.NumberSystem;

import android.util.Log;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Бабайка on 24.09.2016.
 */

public class OctalNumberSystem extends NumberSystem {
    private static String staticRegExp = "[0-7]+([.]?[0-7]+)?";

    public OctalNumberSystem(String num_str) {
        super(num_str);
        super.numberSystem = "7";
    }

    public OctalNumberSystem() {
        super("");
        super.numberSystem = "7";
    }

    @Override
    public HexadecimalNumberSystem toHexadecimal() {
        DecimalNumberSystem dec = new DecimalNumberSystem(this.toDecimal().toString());
        return dec.toHexadecimal();
    }


    public BinaryNumberSystem toBinary() {
        DecimalNumberSystem d = new DecimalNumberSystem(this.toDecimal().toString());
        return new BinaryNumberSystem(d.toBinary().toString());
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
