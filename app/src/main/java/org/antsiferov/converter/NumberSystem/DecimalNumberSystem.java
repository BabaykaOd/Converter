package org.antsiferov.converter.NumberSystem;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Бабайка on 24.09.2016.
 */

public class DecimalNumberSystem extends NumberSystem {
    private static String staticRegExp = "[0-9]+([.]?[0-9]+)?";

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

    public DecimalNumberSystem(String num_str) {
        super(num_str);
        super.numberSystem = "10";
    }

    public DecimalNumberSystem() {
        super("");
        super.numberSystem = "10";
    }

    public BinaryNumberSystem toBinary() {
        String binaryNumber = toBinaryIntPart().toString();
        String fracPart = determiningFractionalPart(2);
        if (fracPart != "") {
            binaryNumber += "." + fracPart;
        }

        return new BinaryNumberSystem(binaryNumber);
    }

    public OctalNumberSystem toOctal() {
        String octalNumber = toOctalIntPart().toString();
        String fractPart = determiningFractionalPart(8);
        if (fractPart != "") {
            octalNumber += "." + fractPart;
        }

        return new OctalNumberSystem(octalNumber);
    }

    public HexadecimalNumberSystem toHexadecimal() {
        String hexNumber = toHexadecimalIntPart().toString();
        String fractPart = determiningHexFractionalPart(16);
        if (fractPart != "") {
            hexNumber += "." + fractPart;
        }

        return new HexadecimalNumberSystem(hexNumber);
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

    private String determiningFractionalPart(int numSys) {
        double doublePartNumber = Double.parseDouble("0." + getDoublePart());
        String fractionalPart = "";
        int i = 0;

        while (doublePartNumber != 0 && i < 8) {
            doublePartNumber *= numSys;
            fractionalPart += (int)doublePartNumber;
            if (doublePartNumber > 0) {
                doublePartNumber -= (int)doublePartNumber;
            }
            i++;
        }

        return fractionalPart;
    }

    private String determiningHexFractionalPart(int numSys) {
        double doublePartNumber = Double.parseDouble("0." + getDoublePart());
        String fractionalHexPart = "";
        int i = 0;

        while (doublePartNumber != 0 && i < 8) {
            doublePartNumber *= numSys;
            fractionalHexPart += hexadecimalNumbers.charAt((int)doublePartNumber);
            if (doublePartNumber > 0) {
                doublePartNumber -= (int)doublePartNumber;
            }
            i++;
        }

        return fractionalHexPart;
    }

    private HexadecimalNumberSystem toHexadecimalIntPart() {
        String hexNum = "";
        int dec_num = Integer.parseInt(getIntPart());

        while (true) {
            hexNum += hexadecimalNumbers.charAt(dec_num % 16);
            dec_num /= 16;
            if (dec_num < 16) {
                if (dec_num != 0) {
                    hexNum += hexadecimalNumbers.charAt(dec_num);
                }
                hexNum = reversion(hexNum);
                break;
            }
        }

        return new HexadecimalNumberSystem(hexNum);
    }

    private OctalNumberSystem toOctalIntPart() {
        int tmp = Integer.parseInt(getIntPart());
        String oct = "";

        while (true) {
            oct += tmp % 8 + "";
            tmp /= 8;
            if (tmp < 8) {
                oct += tmp + "";
                oct = reversion(oct);
                break;
            }
        }
        return new OctalNumberSystem(oct);
    }

    private BinaryNumberSystem toBinaryIntPart() {
        int tmp = Integer.parseInt(getIntPart());
        String bin = "";

        while (true) {
            bin += tmp % 2 + "";
            tmp /= 2;
            if (tmp < 2) {
                bin += tmp + "";
                bin = reversion(bin);
                break;
            }
        }
        return new BinaryNumberSystem(bin);
    }
}