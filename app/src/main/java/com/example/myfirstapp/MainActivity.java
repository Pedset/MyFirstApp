package com.example.myfirstapp;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "MainActivity";

    private SensorManager sensorManager;
    Sensor accelerometer;

    TextView xValue, yValue, zValue;
    ImageView cat_image;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cat_image = findViewById(R.id.cat_img);

        xValue = (TextView) findViewById(R.id.xValue);
        yValue = (TextView) findViewById(R.id.yValue);
        zValue = (TextView) findViewById(R.id.zValue);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(MainActivity.this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);

        cat_image = (ImageView) findViewById(R.id.cat_img);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        xValue.setText("x Value: " + event.values[0]);
        yValue.setText("y Value: " + event.values[1]);
        zValue.setText("z Value: " + event.values[2]);

        float x1 = event.values[2];
        double num1 = x1;
        double gravityOnX = Math.sqrt(Math.pow(9.81, 2)-(Math.pow(((double)event.values[2]), 2)));

        if (event.values[1] > 0){
            cat_image.setRotation((float)(90/gravityOnX) * (float)(event.values[0]));
        }
        else{
            cat_image.setRotation(90 + ( 90 - ((float)(90/gravityOnX) * (float)(event.values[0]))));
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
