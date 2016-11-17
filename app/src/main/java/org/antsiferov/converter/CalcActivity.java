/*
* Добавить статические методы в классны NumberSystem
* Добавить перевод дробных чисел
* Пока программа не работает
* */

package org.antsiferov.converter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.antsiferov.converter.NumberSystem.BinaryNumberSystem;
import org.antsiferov.converter.NumberSystem.DecimalNumberSystem;
import org.antsiferov.converter.NumberSystem.HexadecimalNumberSystem;
import org.antsiferov.converter.NumberSystem.OctalNumberSystem;

import static org.antsiferov.converter.SettingsActivity.APP_PREFERENCES;

public class CalcActivity extends AppCompatActivity implements View.OnClickListener {

    final int MENU_FIRST_NUM_PLUS = 1;
    final int MENU_FIRST_NUM_MINUS = 2;
    final int MENU_SECOND_NUM_PLUS = 3;
    final int MENU_SECOND_NUM_MINUS = 4;
    String signFirstNum = "+", signSecondNum = "+";

    Button btnSum;
    EditText enter_first_num, enter_second_num;
    TextView tvShow, tvSignFirstNum, tvSignSecondNum;
    RadioGroup rgFirst_radio_group, rgSecond_radio_group;
    RadioButton first_binary, first_octal, first_decimal,
            first_hexadecimal, second_binary, second_octal,
            second_decimal, second_hexadecimal;
    LinearLayout CalcLayout;
    SharedPreferences Settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calc_activity);

        btnSum = (Button)findViewById(R.id.calc_sum);
        enter_first_num = (EditText)findViewById(R.id.enter_first_num_sum);
        enter_second_num = (EditText)findViewById(R.id.enter_second_num_sum);
        tvShow = (TextView)findViewById(R.id.show_result_sum);
        tvSignFirstNum = (TextView)findViewById(R.id.tvSignFirstNum);
        tvSignSecondNum = (TextView)findViewById(R.id.tvSignSecondNum);
        rgFirst_radio_group = (RadioGroup)findViewById(R.id.first_radio_group_sum);
        rgSecond_radio_group = (RadioGroup)findViewById(R.id.second_radio_group_sum);
        first_binary = (RadioButton)findViewById(R.id.first_binary_sum);
        first_octal = (RadioButton)findViewById(R.id.first_octal_sum);
        first_decimal = (RadioButton)findViewById(R.id.first_decimal_sum);
        first_hexadecimal = (RadioButton)findViewById(R.id.first_hexadecimal_sum);
        second_binary = (RadioButton)findViewById(R.id.second_binary_sum);
        second_octal = (RadioButton)findViewById(R.id.second_octal_sum);
        second_decimal = (RadioButton)findViewById(R.id.second_decimal_sum);
        second_hexadecimal = (RadioButton)findViewById(R.id.second_hexadecimal_sum);
        CalcLayout = (LinearLayout) findViewById(R.id.CalcLayout);

        Settings = getSharedPreferences(SettingsActivity.APP_PREFERENCES, Context.MODE_PRIVATE);

        btnSum.setOnClickListener(this);

        registerForContextMenu(tvSignFirstNum);
        registerForContextMenu(tvSignSecondNum);
    }

    public void changeBackgroundColor() {
        Settings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        if (Settings.getInt(SettingsActivity.APP_SETTINGS_RED_COLOR, 0) != 0) {
            int red = Settings.getInt(SettingsActivity.APP_SETTINGS_RED_COLOR, 0);
            int green = Settings.getInt(SettingsActivity.APP_SETTINGS_GREEN_COLOR, 0);
            int blue = Settings.getInt(SettingsActivity.APP_SETTINGS_BLUE_COLOR, 0);

            SettingsActivity.updateBackground(CalcLayout, red, green, blue);
        } else {
            SettingsActivity.updateBackground(CalcLayout, 255, 255, 255);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        changeBackgroundColor();
    }

    @Override
    public void onClick(View view) {
        if (!enter_first_num.getText().toString().isEmpty() &&
                !enter_second_num.getText().toString().isEmpty()) {

            String strFirstNum = enter_first_num.getText().toString();
            String strSecondNum = enter_second_num.getText().toString();
            String result = "";
            int firstNumDec = 0, secondNumDec;

            switch (rgFirst_radio_group.getCheckedRadioButtonId()) {
                case R.id.first_binary_sum:
                    if (BinaryNumberSystem.checkingForComplianceWithANumberSystem(strFirstNum)) {
                        BinaryNumberSystem bin_num = new BinaryNumberSystem(strFirstNum);
                        firstNumDec = Integer.parseInt(bin_num.toDecimal().toString());
                    } else {
                        Toast.makeText(CalcActivity.this, "Неверный ввод, введите корректные значения.", Toast.LENGTH_LONG).show();
                    }
                    break;
                case R.id.first_octal_sum:
                    if (OctalNumberSystem.checkingForComplianceWithANumberSystem(strFirstNum)) {
                        OctalNumberSystem oct_num = new OctalNumberSystem(strFirstNum);
                        firstNumDec = Integer.parseInt(oct_num.toDecimal().toString());
                    } else {
                        Toast.makeText(CalcActivity.this, "Неверный ввод, введите корректные значения.", Toast.LENGTH_LONG).show();
                    }
                    break;
                case R.id.first_decimal_sum:
                    if (DecimalNumberSystem.checkingForComplianceWithANumberSystem(strFirstNum)) {
                        DecimalNumberSystem dec_num = new DecimalNumberSystem(strFirstNum);
                        firstNumDec = Integer.parseInt(dec_num.toString());
                    } else {
                        Toast.makeText(CalcActivity.this, "Неверный ввод, введите корректные значения.", Toast.LENGTH_LONG).show();
                    }
                    break;
                case R.id.first_hexadecimal_sum:
                    if (HexadecimalNumberSystem.checkingForComplianceWithANumberSystem(strFirstNum)) {
                        HexadecimalNumberSystem hex_num = new HexadecimalNumberSystem(strFirstNum);
                        firstNumDec = Integer.parseInt(hex_num.toDecimal().toString());
                    } else {
                        Toast.makeText(CalcActivity.this, "Неверный ввод, введите корректные значения.", Toast.LENGTH_LONG).show();
                    }
                    break;
            }

            if (signFirstNum.equals("-")) {
                firstNumDec *= -1;
            }

            switch (rgSecond_radio_group.getCheckedRadioButtonId()) {
                case R.id.second_binary_sum:
                    if (BinaryNumberSystem.checkingForComplianceWithANumberSystem(strSecondNum)) {
                        BinaryNumberSystem bin_num = new BinaryNumberSystem(strSecondNum);
                        secondNumDec = Integer.parseInt(bin_num.toDecimal().toString());
                        if (signSecondNum.equals("-")) {
                            secondNumDec *= -1;
                        }

                        result = DecimalNumberSystem.toBinary((firstNumDec + secondNumDec) + "");
                    } else {
                        Toast.makeText(CalcActivity.this, "Неверный ввод, введите корректные значения.", Toast.LENGTH_LONG).show();
                    }
                    break;
                case R.id.second_octal_sum:
                    if (OctalNumberSystem.checkingForComplianceWithANumberSystem(strSecondNum)) {
                        OctalNumberSystem oct_num = new OctalNumberSystem(strSecondNum);
                        secondNumDec = Integer.parseInt(oct_num.toDecimal().toString());
                        if (signSecondNum.equals("-")) {
                            secondNumDec *= -1;
                        }

                        result = DecimalNumberSystem.toOctal((firstNumDec + secondNumDec) + "");
                    } else {
                        Toast.makeText(CalcActivity.this, "Неверный ввод, введите корректные значения.", Toast.LENGTH_LONG).show();
                    }
                    break;
                case R.id.second_decimal_sum:
                    if (DecimalNumberSystem.checkingForComplianceWithANumberSystem(strSecondNum)) {
                        DecimalNumberSystem dec_num = new DecimalNumberSystem(strSecondNum);
                        secondNumDec = Integer.parseInt(dec_num.toString());
                        if (signSecondNum.equals("-")) {
                            secondNumDec *= -1;
                        }

                        result = (firstNumDec + secondNumDec) + "";
                    } else {
                        Toast.makeText(CalcActivity.this, "Неверный ввод, введите корректные значения.", Toast.LENGTH_LONG).show();
                    }
                    break;
                case R.id.second_hexadecimal_sum:
                    if (HexadecimalNumberSystem.checkingForComplianceWithANumberSystem(strSecondNum)) {
                        HexadecimalNumberSystem hex_num = new HexadecimalNumberSystem(strSecondNum);
                        secondNumDec = Integer.parseInt(hex_num.toDecimal().toString());
                        if (signSecondNum.equals("-")) {
                            secondNumDec *= -1;
                        }
                        result = DecimalNumberSystem.toHexadecimal((firstNumDec + secondNumDec) + "").toString();
                    } else {
                        Toast.makeText(CalcActivity.this, "Неверный ввод, введите корректные значения.", Toast.LENGTH_LONG).show();
                    }
                    break;
            }

            tvShow.setText(result);
        } else {
            Toast.makeText(CalcActivity.this, "Неверный ввод, заполните все поля.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.calc_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_main:
                Intent intentMainActivity = new Intent(this, MainActivity.class);
                startActivity(intentMainActivity);
                break;
            case R.id.menu_bin_converter:
                Intent intentConverterActivity = new Intent(this, ConvertBinaryActivity.class);
                startActivity(intentConverterActivity);
                break;
            case R.id.menu_settings:
                Intent intentSettingActivity = new Intent(this, SettingsActivity.class);
                startActivity(intentSettingActivity);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        switch (v.getId()) {
            case R.id.tvSignFirstNum:
                menu.add(0, MENU_FIRST_NUM_PLUS, 0,"+");
                menu.add(0, MENU_FIRST_NUM_MINUS, 0,"-");
                break;
            case R.id.tvSignSecondNum:
                menu.add(0, MENU_SECOND_NUM_PLUS, 0,"+");
                menu.add(0, MENU_SECOND_NUM_MINUS, 0,"-");
                break;
        }

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case MENU_FIRST_NUM_PLUS:
                signFirstNum = "+";
                tvSignFirstNum.setText("+");
                break;
            case MENU_FIRST_NUM_MINUS:
                signFirstNum = "-";
                tvSignFirstNum.setText("-");
                break;
            case MENU_SECOND_NUM_PLUS:
                signSecondNum = "+";
                tvSignSecondNum.setText("+");
                break;
            case MENU_SECOND_NUM_MINUS:
                signSecondNum = "-";
                tvSignSecondNum.setText("-");
                break;
        }

        return super.onContextItemSelected(item);
    }


}
