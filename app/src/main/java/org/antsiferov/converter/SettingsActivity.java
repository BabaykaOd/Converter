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

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.antsiferov.converter.NumberSystem.DecimalNumberSystem;

public class SettingsActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    int hexColorRed, hexColorGreen, hexColorBlue, color;
    SeekBar sbRed, sbGreen, sbBlue;
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
        //updateBackground();

        Settings = getSharedPreferences(MainActivity.APP_PREFERENCES, Context.MODE_PRIVATE);

        sbRed.setOnSeekBarChangeListener(this);
        sbGreen.setOnSeekBarChangeListener(this);
        sbBlue.setOnSeekBarChangeListener(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        updateBackground();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    private void updateBackground() {
        hexColorRed = sbRed.getProgress();
        hexColorGreen = sbGreen.getProgress();
        hexColorBlue = sbBlue.getProgress();

        color = 0xff000000 + hexColorRed * 0x10000 + hexColorGreen * 0x100
                + hexColorBlue;

        layout_settings.setBackgroundColor(color);
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

        Settings = getSharedPreferences(MainActivity.APP_PREFERENCES, Context.MODE_PRIVATE);

        if (Settings.getInt(MainActivity.APP_PREFERENCES_COLOR, 0) != 0) {
            int color = Settings.getInt(MainActivity.APP_PREFERENCES_COLOR, 0);
            layout_settings.setBackgroundColor(color);
        }

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        SharedPreferences.Editor ed = Settings.edit();
        ed.putInt(MainActivity.APP_PREFERENCES_COLOR, color);
        ed.apply();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
