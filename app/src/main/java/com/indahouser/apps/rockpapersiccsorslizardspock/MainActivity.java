package com.indahouser.apps.rockpapersiccsorslizardspock;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.TriggerEvent;
import android.hardware.TriggerEventListener;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends ActionBarActivity implements SensorEventListener {
    private Game game;
    private Player user;
    private Player computer;
    private Round round;

    private SensorManager mSensorManager;
    private Sensor mSensor;
    private TriggerEventListener mTriggerEventListener;

    private View view;
    private long lastUpdate;
    private boolean color = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        game = new Game();
        user = new Player("User");
        computer = new Player("Computer");
        round = new Round(user, computer);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        /*List<Sensor> deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        for(Sensor sensor : deviceSensors){
            Log.d(sensor.getVendor(), sensor.getName());
        }*/

        setContentView(R.layout.activity_main);
        view = findViewById(R.id.textView);
        //view.setBackgroundColor(Color.GREEN);
        lastUpdate = System.currentTimeMillis();

    }

    public void selectMovement(View view){
        Button button = (Button) view;
        String buttonSelected = (String) button.getTag();
        game.selectButton((Button) button);
        user.setMovement(buttonSelected);
        game.showToast(this, "Shake the phone to play");
        //Toast.makeText(this, "Shake the phone to play", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            getAccelerometer(event);
        }
    }

    private void getAccelerometer(SensorEvent event) {
        float[] values = event.values;
        // Movement
        float x = values[0];
        float y = values[1];
        float z = values[2];

        float accelationSquareRoot = (x * x + y * y + z * z)
                / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);
        long actualTime = event.timestamp;
        if (accelationSquareRoot >= 2) {
            lastUpdate = actualTime;
            round.processWinner();
            Log.d("Winner: ", round.getWinnerMessage());
            Toast.makeText(this, round.getWinnerMessage(), Toast.LENGTH_LONG)
                    .show();
            game.changeBackground(view, round);
            game.resetButtonSelected();
            round.newRound();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // register this class as a listener for the orientation and
        // accelerometer sensors
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // unregister listener
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
}
