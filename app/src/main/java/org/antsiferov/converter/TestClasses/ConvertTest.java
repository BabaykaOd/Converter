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
    private BinaryNumberSystem binaryTest = new BinaryNumberSystem("10100001");
    private OctalNumberSystem octalTest = new OctalNumberSystem("241");
    private DecimalNumberSystem decimalTest = new DecimalNumberSystem("161");
    private HexadecimalNumberSystem hexadecimalTest = new HexadecimalNumberSystem("A1");

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
        Log.d(TAG, "      ");
        Log.d(TAG, "10100001(oct 241) = " + binaryTest.toOctal() + "  binaryTest.toOctal()");
        Log.d(TAG, "10100001(dec 161) = " + binaryTest.toDecimal() + "  binaryTest.toDecimal(");
        Log.d(TAG, "10100001(hex A1) = " + binaryTest.toHexadecimal() + "  binaryTest.toHexadecimal()");
    }

    public void TestOctalConvert() {
        Log.d(TAG, "      ");
        Log.d(TAG, "241(bin 10100001) = " + octalTest.toBinary() + "  octalTest.toBinary()");
        Log.d(TAG, "241(dec 161) = " + octalTest.toDecimal() + "  octalTest.toDecimal()");
        Log.d(TAG, "241(hex A1) = " + octalTest.toHexadecimal() + "  octalTest.oHexadecimal()");
    }

    public void TestDecimalConvert() {
        Log.d(TAG, "      ");
        Log.d(TAG, "161(bin 10100001) = " + decimalTest.toBinary() + "  decimalTest.toBinary()");
        Log.d(TAG, "161(oct 241) = " + decimalTest.toOctal() + "  decimalTest.toOctal()");
        Log.d(TAG, "161(hex A1) = " + decimalTest.toHexadecimal() + "  decimalTest.toHexadecimal()");
    }

    public void TestHexadecimalConvert() {
        Log.d(TAG, "      ");
        Log.d(TAG, "A1(bin 10100001) = " + hexadecimalTest.toBinary() + "  hexadecimalTest.toBinary()");
        Log.d(TAG, "A1(oct 241) = " + hexadecimalTest.toOctal() + "  hexadecimalTest.toOctal()");
        Log.d(TAG, "A1(dec 161) = " + hexadecimalTest.toDecimal() + "  hexadecimalTest.toDecimal()");
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


}
