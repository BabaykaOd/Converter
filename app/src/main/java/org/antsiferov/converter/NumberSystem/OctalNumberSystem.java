package org.antsiferov.converter.NumberSystem;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Бабайка on 24.09.2016.
 */

public class OctalNumberSystem {
    private String num;
    private int number_system = 8;

    public OctalNumberSystem(String str) {
        this.num = str;
    }

    public OctalNumberSystem() {
        this.num = "";
    }

    public boolean isOctal() {
        boolean ok = true;

        if(!num.isEmpty()) {
            Pattern pattern = Pattern.compile("[0-7]+");
            Matcher matcher = pattern.matcher(num);
            if (!matcher.matches()) {
                ok = false;
            }
        } else {
            ok = false;
        }

        return ok;
    }

    public String toDecimal() {
        if (isOctal()) {
            double temp = 0.0;
            int size = num.length() - 1;

            for (int i = size; i > -1; i--) {
                temp += (Math.pow(number_system, i) * (((double)(num.charAt(size - i))) - 48));
            }

            return Integer.toString((int)temp);
        } else {
            return "";
        }
    }

    public String toHexadecimal() {
        if (isOctal()) {
            DecimalNumberSystem dec = new DecimalNumberSystem(this.toDecimal());
            return dec.toHexadecimal();
        } else {
            return "";
        }
    }

    public String toBinary() {
        if (isOctal()) {
            DecimalNumberSystem d = new DecimalNumberSystem(this.toDecimal());

            return d.toBinary();
        } else {
            return "";
        }
    }
}
