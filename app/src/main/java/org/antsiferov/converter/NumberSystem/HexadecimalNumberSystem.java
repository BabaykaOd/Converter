package org.antsiferov.converter.NumberSystem;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Бабайка on 24.09.2016.
 */

public class HexadecimalNumberSystem {
    private String num;
    private int number_system = 16;
    public static final String Hexadecimal = "0123456789ABCDEF";

    public HexadecimalNumberSystem(String num) {
        this.num = num;
    }

    public HexadecimalNumberSystem() {
        this.num = "";
    }

    public boolean isHexadecimal() {
        boolean ok = true;

        if(!num.isEmpty()) {
            Pattern pattern = Pattern.compile("[0-9A-F]+");
            Matcher matcher = pattern.matcher(num);
            if (!matcher.matches()) {
                ok = false;
            }
        } else {
            ok = false;
        }

        return ok;
    }

    static public boolean isHexadecimal(String num) {
        boolean ok = true;

        if(!num.isEmpty()) {
            Pattern pattern = Pattern.compile("[0-9A-F]+");
            Matcher matcher = pattern.matcher(num);
            if (!matcher.matches()) {
                ok = false;
            }
        } else {
            ok = false;
        }

        return ok;
    }

    public String toBinary() {
        if (isHexadecimal()) {
            DecimalNumberSystem dec = new DecimalNumberSystem(this.toDecimal());
            return dec.toBinary();
        } else {
            return "";
        }
    }

    public String toOctal() {
        if (isHexadecimal()) {
            DecimalNumberSystem dec = new DecimalNumberSystem(this.toDecimal());
            return dec.toOctal();
        } else {
            return "";
        }
    }

    public String toDecimal() {
        if (isHexadecimal()) {
            double temp = 0.0;
            int number = 0;
            int size = num.length() - 1;

            for (int i = size; i > -1; i--) {
                if (((double)(num.charAt(size - i))) < 9) {
                    number = (int)num.charAt(size - i);
                } else {
                    for (int j = 0; j < Hexadecimal.length(); j++) {
                        if (Hexadecimal.charAt(j) == num.charAt(size - i)) {
                            number = j;
                        }
                    }
                }
                temp += (Math.pow(number_system, i) * number);
            }
            return Integer.toString((int)temp);
        } else {
            return "";
        }
    }
}
