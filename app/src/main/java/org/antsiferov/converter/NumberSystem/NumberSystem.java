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
            regExp = "[0-" + numberSystem + "]+([.]?[0-" + numberSystem + "]+)?";
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

    public BinaryNumberSystem toBinary() {
        int tmp = Integer.parseInt(number);
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
        return new BinaryNumberSystem(bin);
    }

    public OctalNumberSystem toOctal() {
        int tmp = Integer.parseInt(number);
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
        return new OctalNumberSystem(oct);
    }

    public DecimalNumberSystem toDecimal() {
        double temp = 0.0;
        int size = number.length() - 1;

        for (int i = size; i > -1; i--) {
            temp += (Math.pow(Integer.parseInt(numberSystem) + 1, i) * (((double)(number.charAt(size - i))) - 48));
        }

        return new DecimalNumberSystem(Integer.toString((int)temp));
    }

    public HexadecimalNumberSystem toHexadecimal() {
        String hexNum = "", inverseHexNum = "";
        int dec_num = Integer.parseInt(number);

        while (true) {
            hexNum += hexadecimalNumbers.charAt(dec_num % 16);
            dec_num /= 16;
            if (dec_num < 16) {
                hexNum += hexadecimalNumbers.charAt(dec_num);
                for (int i = hexNum.length() - 1; i != -1; i--) {
                    inverseHexNum += hexNum.charAt(i);
                }
                break;
            }
        }
        return new HexadecimalNumberSystem(inverseHexNum);
    }

    static public String toBinary(String dec_num) {
        int tmp = Integer.parseInt(dec_num);
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
    }

    static public DecimalNumberSystem toDecimal(String str_number) {
        double temp = 0.0;
        int number = 0;
        int size = str_number.length() - 1;

        for (int i = size; i > -1; i--) {
            if (((double)(str_number.charAt(size - i))) < 9) {
                number = (int)str_number.charAt(size - i);
            } else {
                for (int j = 0; j < hexadecimalNumbers.length(); j++) {
                    if (hexadecimalNumbers.charAt(j) == str_number.charAt(size - i)) {
                        number = j;
                    }
                }
            }
            temp += (Math.pow(10, i) * number);
        }
        return new DecimalNumberSystem(Integer.toString((int)temp));
    }

    static public String toOctal(String number) {
        int tmp = Integer.parseInt(number);
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
    }

    static public HexadecimalNumberSystem toHexadecimal(String number) {
        String hex_numbers = "0123456789ABCDEF", hexNum = "", inverseHexNum = "";
        int dec_num = Integer.parseInt(number);

        while (true) {
            hexNum += hex_numbers.charAt(dec_num % 16);
            dec_num /= 16;
            if (dec_num < 16) {
                hexNum += hex_numbers.charAt(dec_num);
                break;
            }
        }

        for (int i = hexNum.length() - 1; i != -1; i--) {
            inverseHexNum += hexNum.charAt(i);
        }

        return new HexadecimalNumberSystem(inverseHexNum);
    }

    public String toString() {
        return number;
    }
}
