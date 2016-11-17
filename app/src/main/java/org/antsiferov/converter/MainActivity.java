package org.antsiferov.converter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.antsiferov.converter.NumberSystem.BinaryNumberSystem;
import org.antsiferov.converter.NumberSystem.DecimalNumberSystem;
import org.antsiferov.converter.NumberSystem.HexadecimalNumberSystem;
import org.antsiferov.converter.NumberSystem.OctalNumberSystem;
import org.antsiferov.converter.TestClasses.ConvertTest;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button calc;
    EditText enter;
    TextView show;
    RadioGroup first_radio_group, second_radio_group;
    RadioButton first_binary, first_octal, first_decimal,
            first_hexadecimal, second_binary, second_octal,
            second_decimal, second_hexadecimal;
    LinearLayout MainLayout;
    SharedPreferences Settings;

    ConvertTest test = new ConvertTest();

    private String TAG = "myLogs";
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calc = (Button) findViewById(R.id.calc);
        enter = (EditText) findViewById(R.id.enter_num);
        show = (TextView) findViewById(R.id.show_result);
        first_radio_group = (RadioGroup) findViewById(R.id.first_radio_group);
        second_radio_group = (RadioGroup) findViewById(R.id.second_radio_group);
        first_binary = (RadioButton) findViewById(R.id.first_binary);
        first_octal = (RadioButton) findViewById(R.id.first_octal);
        first_decimal = (RadioButton) findViewById(R.id.first_decimal);
        first_hexadecimal = (RadioButton) findViewById(R.id.first_hexadecimal);
        second_binary = (RadioButton) findViewById(R.id.second_binary);
        second_octal = (RadioButton) findViewById(R.id.second_octal);
        second_decimal = (RadioButton) findViewById(R.id.second_decimal);
        second_hexadecimal = (RadioButton) findViewById(R.id.second_hexadecimal);
        MainLayout = (LinearLayout) findViewById(R.id.MainLayout);

        Settings = getSharedPreferences(SettingsActivity.APP_PREFERENCES, Context.MODE_PRIVATE);

        test.TestCheckingForComplianceWithANumberSystemReturnTrue();
        test.TestAllNumberSystemToConvert();

        calc.setOnClickListener(this);

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }



    @Override
    public void onClick(View view) {
        String str = enter.getText().toString();

        if (first_binary.isChecked()) {
            BinaryNumberSystem bin_num = new BinaryNumberSystem(str);

            if (bin_num.checkingForComplianceWithANumberSystem()) {
                if (second_octal.isChecked()) {
                    show.setText(bin_num.toOctal().toString());
                } else if (second_decimal.isChecked()) {
                    show.setText(bin_num.toDecimal().toString());
                } else if (second_hexadecimal.isChecked()) {
                    show.setText(bin_num.toHexadecimal().toString());
                }
            } else {
                Toast.makeText(MainActivity.this, "Неверный ввод, попробуйте еще раз.", Toast.LENGTH_LONG).show();
                show.setText("ERROR");
            }
        } else if (first_octal.isChecked()) {
            OctalNumberSystem oct_num = new OctalNumberSystem(str);

            if (oct_num.checkingForComplianceWithANumberSystem()) {
                if (second_binary.isChecked()) {
                    show.setText(oct_num.toBinary().formatOutput());
                } else if (second_decimal.isChecked()) {
                    show.setText(oct_num.toDecimal().toString());
                } else if (second_hexadecimal.isChecked()) {
                    show.setText(oct_num.toHexadecimal().toString());
                }
            } else {
                show.setText("ERROR");
                Toast.makeText(MainActivity.this, "Неверный ввод, попробуйте еще раз.", Toast.LENGTH_LONG).show();
            }
        } else if (first_decimal.isChecked()) {
            DecimalNumberSystem dec_num = new DecimalNumberSystem(str);

            if (dec_num.checkingForComplianceWithANumberSystem()) {
                if (second_octal.isChecked()) {
                    show.setText(dec_num.toOctal().toString());
                } else if (second_binary.isChecked()) {
                    show.setText(dec_num.toBinary().formatOutput());
                } else if (second_hexadecimal.isChecked()) {
                    show.setText(dec_num.toHexadecimal().toString());
                }
            } else {
                show.setText("ERROR");
                Toast.makeText(MainActivity.this, "Неверный ввод, попробуйте еще раз.", Toast.LENGTH_LONG).show();
            }
        } else if (first_hexadecimal.isChecked()) {
            HexadecimalNumberSystem hex_num = new HexadecimalNumberSystem(str);

            if (hex_num.checkingForComplianceWithANumberSystem()) {
                if (second_binary.isChecked()) {
                    show.setText(hex_num.toBinary().formatOutput());
                } else if (second_octal.isChecked()) {
                    show.setText(hex_num.toOctal().toString());
                } else if (second_decimal.isChecked()) {
                    show.setText(hex_num.toDecimal().toString());
                }
            } else {
                show.setText("ERROR");
                Toast.makeText(MainActivity.this, "Неверный ввод, попробуйте еще раз.", Toast.LENGTH_LONG).show();
            }
        }

    }

    public void changeBackgroundColor() {
        if (Settings.getInt(SettingsActivity.APP_SETTINGS_RED_COLOR, 0) != 0) {
            int red = Settings.getInt(SettingsActivity.APP_SETTINGS_RED_COLOR, 0);
            int green = Settings.getInt(SettingsActivity.APP_SETTINGS_GREEN_COLOR, 0);
            int blue = Settings.getInt(SettingsActivity.APP_SETTINGS_BLUE_COLOR, 0);

            SettingsActivity.updateBackground(MainLayout, red, green, blue);
        } else {
            SettingsActivity.updateBackground(MainLayout, 255, 255, 255);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_calc:
                Intent intentCalcActivity = new Intent(this, CalcActivity.class);
                startActivity(intentCalcActivity);
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

    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    @Override
    protected void onResume() {
        super.onResume();

        changeBackgroundColor();
    }
}