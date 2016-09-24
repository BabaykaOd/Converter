package org.antsiferov.converter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.antsiferov.converter.NumberSystem.BinaryNumberSystem;
import org.antsiferov.converter.NumberSystem.DecimalNumberSystem;
import org.antsiferov.converter.NumberSystem.HexadecimalNumberSystem;
import org.antsiferov.converter.NumberSystem.OctalNumberSystem;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button calc;
    EditText enter;
    TextView show;
    RadioGroup first_radio_group, second_radio_group;
    RadioButton first_binary, first_octal, first_decimal,
            first_hexadecimal, second_binary, second_octal,
            second_decimal, second_hexadecimal;
    private String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calc = (Button)findViewById(R.id.calc);
        enter = (EditText)findViewById(R.id.enter_num);
        show = (TextView)findViewById(R.id.show_result);
        first_radio_group = (RadioGroup)findViewById(R.id.first_radio_group);
        second_radio_group = (RadioGroup)findViewById(R.id.second_radio_group);
        first_binary = (RadioButton)findViewById(R.id.first_binary);
        first_octal = (RadioButton)findViewById(R.id.first_octal);
        first_decimal = (RadioButton)findViewById(R.id.first_decimal);
        first_hexadecimal = (RadioButton)findViewById(R.id.first_hexadecimal);
        second_binary = (RadioButton)findViewById(R.id.second_binary);
        second_octal = (RadioButton)findViewById(R.id.second_octal);
        second_decimal = (RadioButton)findViewById(R.id.second_decimal);
        second_hexadecimal = (RadioButton)findViewById(R.id.second_hexadecimal);

        calc.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String str = enter.getText().toString();

        if (first_binary.isChecked()) {
            BinaryNumberSystem bin_num = new BinaryNumberSystem(str);

            if (bin_num.isBinary()) {
                if (second_octal.isChecked()) {
                    show.setText(bin_num.toOctal());
                } else if (second_decimal.isChecked()) {
                    show.setText(bin_num.toDecimal());
                } else if (second_hexadecimal.isChecked()) {
                    show.setText(bin_num.toHexadecimal());
                }
            } else {
                Toast.makeText(MainActivity.this, "Неверный ввод, попробуйте еще раз.", Toast.LENGTH_LONG).show();
            }
        } else if (first_octal.isChecked()) {
            OctalNumberSystem oct = new OctalNumberSystem(str);

            if (oct.isOctal()) {
                if (second_binary.isChecked()) {
                    show.setText(oct.toBinary());
                } else if (second_decimal.isChecked()) {
                    show.setText(oct.toDecimal());
                } else if (second_hexadecimal.isChecked()) {
                    show.setText(oct.toHexadecimal());
                }
            } else {
                Toast.makeText(MainActivity.this, "Неверный ввод, попробуйте еще раз.", Toast.LENGTH_LONG).show();
            }
        } else if (first_decimal.isChecked()) {
            DecimalNumberSystem dec_num = new DecimalNumberSystem(str);

            Log.d(TAG, "str = " + str);

            if (dec_num.isDecimal()) {
                if (second_octal.isChecked()) {
                    show.setText(dec_num.toOctal());
                } else if (second_binary.isChecked()) {
                    show.setText(dec_num.toBinary());
                } else if (second_hexadecimal.isChecked()) {
                    show.setText(dec_num.toHexadecimal());
                }
            } else {
                Toast.makeText(MainActivity.this, "Неверный ввод, попробуйте еще раз.", Toast.LENGTH_LONG).show();
            }
        } else if (first_hexadecimal.isChecked()) {
            HexadecimalNumberSystem hex_num = new HexadecimalNumberSystem(str);

            if (hex_num.isHexadecimal()) {
                if (second_binary.isChecked()) {
                    show.setText(hex_num.toBinary());
                } else if (second_octal.isChecked()) {
                    show.setText(hex_num.toOctal());
                } else if (second_decimal.isChecked()) {
                    show.setText(hex_num.toDecimal());
                }
            } else {
                Toast.makeText(MainActivity.this, "Неверный ввод, попробуйте еще раз.", Toast.LENGTH_LONG).show();
            }
        }

    }
}