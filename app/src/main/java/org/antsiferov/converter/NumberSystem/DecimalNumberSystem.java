package org.antsiferov.converter.NumberSystem;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Бабайка on 24.09.2016.
 */

public class DecimalNumberSystem {
    private String num;
    private int number_system = 10;

    public DecimalNumberSystem(String str) {
        num = str;
    }

    public DecimalNumberSystem() {
        num = "";
    }

    public boolean isDecimal() {
        boolean ok = true;

        if(!num.isEmpty()) {
            Pattern pattern = Pattern.compile("[0-9]+");
            Matcher matcher = pattern.matcher(num);
            if (!matcher.matches()) {
                ok = false;
            }
        } else {
            ok = false;
        }

        return ok;
    }

    public String toOctal() {
        if (isDecimal()) {
            int tmp = Integer.parseInt(num);
            String oct = "";
            boolean flag = true;

            while (flag) {
                oct += tmp % 8 + "";
                tmp /= 8;
                if (tmp < 8) {
                    oct += tmp + "";
                    String b = "";
                    int size = oct.length() - 1;

                    for (int i = size; i != -1; i--) {
                        b += oct.charAt(i);
                    }
                    oct = b;
                    flag = false;
                }
            }
            return oct;
        } else {
            return "";
        }
    }

    public String toBinary() {
        if (isDecimal()) {
            int tmp = Integer.parseInt(num);
            String bin = "";
            boolean flag = true;

            while (flag) {
                bin += tmp % 2 + "";
                tmp /= 2;
                if (tmp < 2) {
                    bin += tmp + "";
                    String b = "";
                    int size = bin.length() - 1;

                    for (int i = size; i != -1; i--) {
                        b += bin.charAt(i);
                    }
                    bin = b;
                    flag = false;
                }
            }
            return bin;
        } else {
            return "";
        }
    }


    public String toHexadecimal() {
        if(isDecimal()) {
            Double tmp = Double.parseDouble(num);
            String hex_numbers = "0123456789ABCDEF";
            String octal = ((int)(tmp / 16) + "" + hex_numbers.charAt((int)(tmp % 16)) + "");
            return octal;
        } else {
            return "";
        }
    }
}
