package org.antsiferov.converter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.antsiferov.converter.NumberSystem.DecimalNumberSystem;

public class SettingsActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    public static  final String APP_SETTINGS_RED_COLOR = "redColor";
    public static  final String APP_SETTINGS_GREEN_COLOR = "greenColor";
    public static  final String APP_SETTINGS_BLUE_COLOR = "blueColor";
    public static final String APP_PREFERENCES = "ConverterSettings";

    int hexColorRed, hexColorGreen, hexColorBlue;
    SeekBar sbRed, sbGreen, sbBlue;
    TextView tvRedShowColor, tvGreenShowColor, tvBlueShowColor, tvMainColorShow;
    LinearLayout layout_settings;

    SharedPreferences Settings;

    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_settings);

        sbRed = (SeekBar) findViewById(R.id.sbRed);
        sbGreen = (SeekBar) findViewById(R.id.sbGreen);
        sbBlue = (SeekBar) findViewById(R.id.sbBlue);
        layout_settings = (LinearLayout) findViewById(R.id.layout_settings);
        tvRedShowColor = (TextView)findViewById(R.id.tvRedShowColor);
        tvGreenShowColor = (TextView)findViewById(R.id.tvGreenShowColor);
        tvBlueShowColor = (TextView)findViewById(R.id.tvBlueShowColor);
        tvMainColorShow = (TextView)findViewById(R.id.tvMainColorShow);

        Settings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        sbRed.setOnSeekBarChangeListener(this);
        sbGreen.setOnSeekBarChangeListener(this);
        sbBlue.setOnSeekBarChangeListener(this);

        changeBackgroundColor(layout_settings);

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public static void updateBackground(LinearLayout layout, int red, int green, int blue) {
        layout.setBackgroundColor(RGBConvert(red, green, blue));
    }

    public static int RGBConvert(int redColor, int greenColor, int blueColor) {
        return 0xff000000 + redColor * 0x10000 + greenColor * 0x100 + blueColor;
    }

    public void changeBackgroundColor(LinearLayout layout) {
        Settings = getSharedPreferences(SettingsActivity.APP_PREFERENCES, Context.MODE_PRIVATE);

        if (Settings.getInt(APP_SETTINGS_RED_COLOR, 0) != 0) {
            int red = Settings.getInt(APP_SETTINGS_RED_COLOR, 0);
            int green = Settings.getInt(APP_SETTINGS_GREEN_COLOR, 0);
            int blue = Settings.getInt(APP_SETTINGS_BLUE_COLOR, 0);

            sbRed.setProgress(red);
            sbGreen.setProgress(green);
            sbBlue.setProgress(blue);

            updateBackground(layout, red, green, blue);
        } else {
            updateBackground(layout, 255, 255, 255);
        }
    }

    private String convertToHexColor(int red, int green, int blue) {
        String color = "#FF";

        color += DecimalNumberSystem.toHexadecimal(red + "");
        color += DecimalNumberSystem.toHexadecimal(green + "");
        color += DecimalNumberSystem.toHexadecimal(blue + "");

        return color;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        hexColorRed = sbRed.getProgress();
        hexColorGreen = sbGreen.getProgress();
        hexColorBlue = sbBlue.getProgress();

        tvRedShowColor.setText(hexColorRed + "");
        tvGreenShowColor.setText(hexColorGreen + "");
        tvBlueShowColor.setText(hexColorBlue + "");

        tvMainColorShow.setText(convertToHexColor(hexColorRed, hexColorGreen, hexColorBlue));

        updateBackground(layout_settings, hexColorRed, hexColorGreen, hexColorBlue);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Settings Page") // TODO: Define a title for the content shown.
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

        changeBackgroundColor(layout_settings);

        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        SharedPreferences.Editor ed = Settings.edit();
        ed.putInt(APP_SETTINGS_RED_COLOR, hexColorRed);
        ed.putInt(APP_SETTINGS_GREEN_COLOR, hexColorGreen);
        ed.putInt(APP_SETTINGS_BLUE_COLOR, hexColorBlue);
        ed.apply();

        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
