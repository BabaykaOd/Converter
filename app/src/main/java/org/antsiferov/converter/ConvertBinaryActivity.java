package org.antsiferov.converter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ConvertBinaryActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvAdditionalCodeShow, tvInverseCodeShow;
    EditText etEnterBinaryNumber;
    SharedPreferences Settings;
    LinearLayout ConvertLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.convert_binary_activity);

        tvAdditionalCodeShow = (TextView)findViewById(R.id.tvAdditionalCodeShow);
        tvInverseCodeShow = (TextView)findViewById(R.id.tvInverseCodeShow);
        etEnterBinaryNumber = (EditText)findViewById(R.id.etEnterBinaryNumber);
        ConvertLayout = (LinearLayout) findViewById(R.id.convert_binary_activity);

        Settings = getSharedPreferences(SettingsActivity.APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    public void changeBackgroundColor() {
        if (Settings.getInt(SettingsActivity.APP_SETTINGS_RED_COLOR, 0) != 0) {
            int red = Settings.getInt(SettingsActivity.APP_SETTINGS_RED_COLOR, 0);
            int green = Settings.getInt(SettingsActivity.APP_SETTINGS_GREEN_COLOR, 0);
            int blue = Settings.getInt(SettingsActivity.APP_SETTINGS_BLUE_COLOR, 0);

            SettingsActivity.updateBackground(ConvertLayout, red, green, blue);
        } else {
            SettingsActivity.updateBackground(ConvertLayout, 255, 255, 255);
        }
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.convert_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_calc:
                Intent intentCalcActivity = new Intent(this, CalcActivity.class);
                startActivity(intentCalcActivity);
                break;
            case R.id.menu_main:
                Intent intentMainActivity = new Intent(this, MainActivity.class);
                startActivity(intentMainActivity);
                break;
            case R.id.menu_settings:
                Intent intentSettingActivity = new Intent(this, SettingsActivity.class);
                startActivity(intentSettingActivity);
                break;
        }



        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        changeBackgroundColor();
    }
}
