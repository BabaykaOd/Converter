package org.antsiferov.converter.NumberSystem;

import android.util.Log;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.ContentValues.TAG;

/**
 * Created by Бабайка on 24.09.2016.
 */

public class BinaryNumberSystem extends NumberSystem {
    private static String staticRegExp = "[0-1]+([.]?[0-1]+)?";
    private HashMap<String, String> binToHax = new HashMap<>();
    private HashMap<String, String> binToOct = new HashMap<>();

    public BinaryNumberSystem(String num_str) {
        super(num_str);
        this.numberSystem = "1";
    }

    public BinaryNumberSystem() {
        super("");
        super.numberSystem = "1";
    }

    @Override
    public OctalNumberSystem toOctal() {
        int sizeNumber;
        String formatNumber;
        String triad;
        String octNumber = "";
        binToOct.put("000", "0");
        binToOct.put("001", "1");
        binToOct.put("010", "2");
        binToOct.put("011", "3");
        binToOct.put("101", "5");
        binToOct.put("100", "4");
        binToOct.put("110", "6");
        binToOct.put("111", "7");



        formatNumber = formatStringToConvert(3);
        sizeNumber = formatNumber.length();

        for (int i = 0; i < sizeNumber; i+=3) {
            triad = formatNumber.substring(i, i + 3);
            octNumber += binToOct.get(triad);
        }

        return new OctalNumberSystem(octNumber);
    }

    @Override
    public HexadecimalNumberSystem toHexadecimal() {
        int sizeNumber;
        String formatNumber;
        String tetrad;
        String hexNumber = "";
        binToHax.put("0000", "0");
        binToHax.put("0001", "1");
        binToHax.put("0010", "2");
        binToHax.put("0011", "3");
        binToHax.put("0100", "4");
        binToHax.put("0101", "5");
        binToHax.put("0110", "6");
        binToHax.put("0111", "7");
        binToHax.put("1000", "8");
        binToHax.put("1001", "9");
        binToHax.put("1010", "A");
        binToHax.put("1011", "B");
        binToHax.put("1100", "C");
        binToHax.put("1101", "D");
        binToHax.put("1110", "E");
        binToHax.put("1111", "F");

        formatNumber = formatStringToConvert(4);
        sizeNumber = formatNumber.length();

        for (int i = 0; i < sizeNumber; i+=4) {
            tetrad = formatNumber.substring(i, i + 4);
            hexNumber += binToHax.get(tetrad);
        }

        return new HexadecimalNumberSystem(hexNumber);
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

    public String formatOutput() {
        String temp = "", revers = "";

        for (int i = number.length() - 1; i != -1; i--) {
            revers += number.charAt(i);
        }

        for (int i = revers.length() - 1; i != -1; i--) {
            if ((i + 1) % 4 == 0 && i != 0) {
                temp += " ";
            }
            temp += revers.charAt(i);
        }

        return temp;
    }

    private String formatStringToConvert(int bits) {
        String reverse = reversion(number);
        if (number.length() % bits != 0) {
            while (reverse.length() % bits != 0) {
                reverse += "0";
            }
        }

        reverse = reversion(reverse);

        return reverse;
    }

    public String conversionToInversionCode() {
        String inversionNumber = "";
        int lengthNum = this.number.length();

        for(int i = 0; i < lengthNum; i++) {
            inversionNumber += inversionBit(this.number.charAt(i));
        }

        return inversionNumber;
    }

    public String conversionToAdditionalCode() {
        String conversionNum = conversionToInversionCode();
        String additionalNum = "";
        HashMap<String, String> tmp;
        String remainder = "0";
        int lengthNum = conversionNum.length() - 1;

        tmp =  binaryAdd(conversionNum.charAt(lengthNum), '1', remainder);
        additionalNum += tmp.get("result");
        remainder = tmp.get("remainder");

        for (int i = lengthNum - 2; i > -1; i--) {
            tmp =  binaryAdd(conversionNum.charAt(lengthNum), '0', remainder);
            additionalNum += tmp.get("result");
            remainder = tmp.get("remainder");
        }
        if (remainder == "1") {
            additionalNum += "1";
        }



        additionalNum = reversion(additionalNum);

        return additionalNum;
    }

    private  HashMap<String, String> binaryAdd(char firstAddend, char secondAddend, String remainder) {
        HashMap<String, String> result = new HashMap<>();

        if (firstAddend == secondAddend) {
            result.put("result", Character.toString(firstAddend));
            result.put("remainder", Character.toString(firstAddend));
        } else {
            if (remainder == "1") {
                result.put("result", "0");
                result.put("remainder", "1");
            } else {
                result.put("result", "1");
                result.put("remainder", "0");
            }
        }

        return result;
    }

    private String reversion(String str) {
        String tmp = "";
        for (int i = str.length() - 1; i > -1; i--) {
            tmp += str.charAt(i);
        }
        return tmp;
    }

    private String inversionBit(char bit) {
        if (bit == '0') {
           return "1";
        } else {
            return "0";
        }
    }
}
