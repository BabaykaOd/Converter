package org.antsiferov.converter.TestClasses;

import android.util.Log;

import org.antsiferov.converter.NumberSystem.BinaryNumberSystem;
import org.antsiferov.converter.NumberSystem.DecimalNumberSystem;
import org.antsiferov.converter.NumberSystem.HexadecimalNumberSystem;
import org.antsiferov.converter.NumberSystem.OctalNumberSystem;

/**
 * Created by Бабайка on 05.11.2016.
 */

public class ConvertTest {
    private String TAG = "testLogs";

    public void TestBinaryCheckingForComplianceReturnTrue() {
        Log.d(TAG, "\n(true) - " + BinaryNumberSystem.checkingForComplianceWithANumberSystem("11101.1") + " BinNum.checkingForComplianceWithANumberSystem(11101.1)");
        Log.d(TAG, "(false) - " + BinaryNumberSystem.checkingForComplianceWithANumberSystem("11201.1") + " BinNum.checkingForComplianceWithANumberSystem(11201.1)");
    }

    public void TestOctalCheckingForComplianceReturnTrue() {
        Log.d(TAG, "\n(true) - " + OctalNumberSystem.checkingForComplianceWithANumberSystem("653.15") + " OctNum.checkingForComplianceWithANumberSystem(653.15)");
        Log.d(TAG, "(false) - " + OctalNumberSystem.checkingForComplianceWithANumberSystem("854,15") + " OctNum.checkingForComplianceWithANumberSystem(854,15)");
    }

    public void TestDecimalCheckingForComplianceReturnTrue() {
        Log.d(TAG, "\n(true) - " + DecimalNumberSystem.checkingForComplianceWithANumberSystem("2487.15") + " DecNum.checkingForComplianceWithANumberSystem(2487.15)");
        Log.d(TAG, "(false) - " + DecimalNumberSystem.checkingForComplianceWithANumberSystem("2487,15") + " DecNum.checkingForComplianceWithANumberSystem(2487,15)");
    }

    public void TestHexDecimalCheckingForComplianceReturnTrue() {
        Log.d(TAG, "\n(true) - " + HexadecimalNumberSystem.checkingForComplianceWithANumberSystem("FF.AD") + " HexNum.checkingForComplianceWithANumberSystem(FF.AD)");
        Log.d(TAG, "(false) - " + HexadecimalNumberSystem.checkingForComplianceWithANumberSystem("Ff,Ad") + " HexNum.checkingForComplianceWithANumberSystem(Ff,Ad)");
    }

    public void TestBinaryConvert() {
        BinaryNumberSystem binaryTest = new BinaryNumberSystem("10100001");
        Log.d(TAG, "      ");
        Log.d(TAG, "10100001(oct 241) = " + binaryTest.toOctal() + "  binaryTest.toOctal()");
        Log.d(TAG, "10100001(dec 161) = " + binaryTest.toDecimal() + "  binaryTest.toDecimal(");
        Log.d(TAG, "10100001(hex A1) = " + binaryTest.toHexadecimal() + "  binaryTest.toHexadecimal()");
    }

    public void TestOctalConvert() {
        OctalNumberSystem octalTest = new OctalNumberSystem("241");
        Log.d(TAG, "      ");
        Log.d(TAG, "241(bin 10100001) = " + octalTest.toBinary() + "  octalTest.toBinary()");
        Log.d(TAG, "241(dec 161) = " + octalTest.toDecimal() + "  octalTest.toDecimal()");
        Log.d(TAG, "241(hex A1) = " + octalTest.toHexadecimal() + "  octalTest.oHexadecimal()");
    }

    public void TestDecimalConvert() {
        DecimalNumberSystem decimalTest = new DecimalNumberSystem("161");
        Log.d(TAG, "      ");
        Log.d(TAG, "161(bin 10100001) = " + decimalTest.toBinary() + "  decimalTest.toBinary()");
        Log.d(TAG, "161(oct 241) = " + decimalTest.toOctal() + "  decimalTest.toOctal()");
        Log.d(TAG, "161(hex A1) = " + decimalTest.toHexadecimal() + "  decimalTest.toHexadecimal()");
    }

    public void TestHexadecimalConvert() {
        HexadecimalNumberSystem hexadecimalTest = new HexadecimalNumberSystem("A1");
        Log.d(TAG, "      ");
        Log.d(TAG, "A1(bin 10100001) = " + hexadecimalTest.toBinary() + "  hexadecimalTest.toBinary()");
        Log.d(TAG, "A1(oct 241) = " + hexadecimalTest.toOctal() + "  hexadecimalTest.toOctal()");
        Log.d(TAG, "A1(dec 161) = " + hexadecimalTest.toDecimal() + "  hexadecimalTest.toDecimal()");
    }

    public void TestDecimalConvertDoubleNumber() {
        DecimalNumberSystem decimalDoubleTest = new DecimalNumberSystem("10.25");
        Log.d(TAG, "      ");
        Log.d(TAG, "10.25(bin 1010.01) = " + decimalDoubleTest.toBinary() + "  decimalDoubleTest.toBinary()");
        Log.d(TAG, "10.25(oct 12.2) = " + decimalDoubleTest.toOctal() + "  decimalDoubleTest.toBinary()");
        Log.d(TAG, "10.25(hex A.4) = " + decimalDoubleTest.toHexadecimal() + "  decimalDoubleTest.toHexadecimal()");
    }

    public void TestBinaryConvertDoubleNumber() {
        BinaryNumberSystem binaryDoubleTest = new BinaryNumberSystem("1010.01");
        Log.d(TAG, "      ");
        Log.d(TAG, "1010.01(dec = 10.25) = " +  binaryDoubleTest.toDecimal() + "  binaryDoubleTest.toDecimal()");
        Log.d(TAG, "1010.01(oct = 12.2) = " +  binaryDoubleTest.toOctal() + "  binaryDoubleTest.toOctal()");
        Log.d(TAG, "1010.01(hex = A.4) = " +  binaryDoubleTest.toHexadecimal() + "  binaryDoubleTest.toHexadecimal()");
    }

    public void TestOctalConvertDoubleNumber() {
        OctalNumberSystem octalDoubleTest = new OctalNumberSystem("12.2");
        Log.d(TAG, "      ");
        Log.d(TAG, "12.2(bin = 1010.01) = " +  octalDoubleTest.toBinary() + "  octalDoubleTest.toBinary()");
        Log.d(TAG, "12.2(dec = 10.25) = " +  octalDoubleTest.toDecimal() + "  octalDoubleTest.toDecimal()");
        Log.d(TAG, "12.2(hex = A.4) = " +  octalDoubleTest.toHexadecimal() + "  octalDoubleTest.toHexadecimal()");
    }

    public void TestHexadecimalConvertDoubleNumber() {
        HexadecimalNumberSystem hexadecimalDoubleTest = new HexadecimalNumberSystem("A.4");
        Log.d(TAG, "      ");
        Log.d(TAG, "A.4(bin = 1010.01) = " +  hexadecimalDoubleTest.toBinary() + "  hexadecimalDoubleTest.toBinary()");
        Log.d(TAG, "A.4(oct = 12.2) = " +  hexadecimalDoubleTest.toOctal() + "  hexadecimalDoubleTest.toOctal()");
        Log.d(TAG, "A.4(dec = 10.25) = " +  hexadecimalDoubleTest.toDecimal() + "  hexadecimalDoubleTest.toDecimal()");
    }

    public void TestAllNumberSystemToConvert() {
        Log.d(TAG, "      ");
        Log.d(TAG, "\n------TestAllNumberSystemToConvert-----");
        TestBinaryConvert();
        TestOctalConvert();
        TestDecimalConvert();
        TestHexadecimalConvert();
    }

    public void TestCheckingForComplianceWithANumberSystemReturnTrue() {
        Log.d(TAG, "      ");
        Log.d(TAG, "\n---TestCheckingForComplianceWithANumberSystemReturnTrue---");
        TestBinaryCheckingForComplianceReturnTrue();
        TestOctalCheckingForComplianceReturnTrue();
        TestDecimalCheckingForComplianceReturnTrue();
        TestHexDecimalCheckingForComplianceReturnTrue();
    }

    public void TestAllNumberSystemToConvertDoubleNumber() {
        Log.d(TAG, "      ");
        Log.d(TAG, "\n------TestAllNumberSystemToConvertDoubleNumber-----");
        TestDecimalConvertDoubleNumber();
        TestBinaryConvertDoubleNumber();
        TestOctalConvertDoubleNumber();
        TestHexadecimalConvertDoubleNumber();
    }

}
