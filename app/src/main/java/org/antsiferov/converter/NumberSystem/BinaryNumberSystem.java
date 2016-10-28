package org.antsiferov.converter.NumberSystem;

/**
 * Created by Бабайка on 24.09.2016.
 */

public class BinaryNumberSystem {
    private String num;
    private int number_system = 2;

    public BinaryNumberSystem(String str) {
        num = str;
    }

    public BinaryNumberSystem() {
        num = "";
    }

    public String toOctal() {
        if (isBinary()) {
            DecimalNumberSystem dec = new DecimalNumberSystem(this.toDecimal());
            return dec.toOctal();
        } else {
            return "";
        }
    }

    public String toDecimal() {
        if (isBinary()) {
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
        if (isBinary()) {
            DecimalNumberSystem dec = new DecimalNumberSystem(this.toDecimal());
            return dec.toHexadecimal();
        } else {
            return "";
        }
    }

    public boolean isBinary() {
        boolean ok = true;
        int size = num.length();

        if(!num.isEmpty()) {
            for (int i = 0; i < size; i++) {
                if (num.charAt(i) != '0' && num.charAt(i) != '1') {
                    ok = false;
                    break;
                }
            }
        } else {
            ok = false;
        }

        return ok;
    }

    static public boolean isBinary(String number) {
        boolean ok = true;
        int size = number.length();

        if(!number.isEmpty()) {
            for (int i = 0; i < size; i++) {
                if (number.charAt(i) != '0' && number.charAt(i) != '1') {
                    ok = false;
                    break;
                }
            }
        } else {
            ok = false;
        }

        return ok;
    }
}
