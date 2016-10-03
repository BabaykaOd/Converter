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
    private String TAG = "myLogs";

    public DecimalNumberSystem(String num_str) {
        this.num = num_str;
    }

    public DecimalNumberSystem() {
        this.num = "";
    }

    public boolean isDecimal() {
        if(!num.isEmpty()) {
            Pattern pattern = Pattern.compile("[0-9]+");
            Matcher matcher = pattern.matcher(num);
            if (!matcher.matches()) {
                return false;
            }
        } else {
            return false;
        }

        return true;
    }

    static public boolean isDecimal(String dec_num) {
        if(!dec_num.isEmpty()) {
            Pattern pattern = Pattern.compile("[0-9]+");
            Matcher matcher = pattern.matcher(dec_num);
            if (!matcher.matches()) {
                return false;
            }
        } else {
            return false;
        }

        return true;
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


     static public String toBinary(String dec_num) {
         if (DecimalNumberSystem.isDecimal(dec_num)) {
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
         } else {
             return "";
         }
    }

    public String toHexadecimal() {
        if(isDecimal()) {
            int dec_num = Integer.parseInt(num);
            String hex_numbers = "0123456789ABCDEF", hexNum = "", inverseHexNum = "";

            while (true) {
                hexNum += hex_numbers.charAt(dec_num % 16);
                dec_num /= 16;
                if (dec_num < 16) {
                    hexNum += dec_num;
                    for (int i = hexNum.length() - 1; i != -1; i--) {
                        inverseHexNum += hexNum.charAt(i);
                    }
                    break;
                }
            }

            return inverseHexNum;
        } else {
            return "";
        }
    }

    public String getNumber() {
        return this.num;
    }
}