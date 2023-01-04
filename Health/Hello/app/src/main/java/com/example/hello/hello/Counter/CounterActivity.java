package com.example.hello.hello.Counter;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hello.hello.LoginSignup.DailyCalorie;
import com.example.hello.hello.R;

import pl.pawelkleczkowski.customgauge.CustomGauge;

public class CounterActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;
    TextView steps;
    boolean running = false;
    CustomGauge gauge;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);
        steps = findViewById(R.id.steps_id);
        gauge = findViewById(R.id.progressbar_id);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        running = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        if(countSensor!=null){
            sensorManager.registerListener(this,countSensor,SensorManager.SENSOR_DELAY_UI);
        }else {
            Toast.makeText(getApplicationContext(),"Sensor not found",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        running = false;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(running){
                String steps_run = String.valueOf(event.values[0]);
                int step = Math.round(Float.parseFloat(steps_run));
                String steps_Run = Integer.toString(step);
                steps.setText(steps_Run.concat(" steps"));

                float burned = Float.parseFloat(steps_run) / 20;


                int progress = Math.round(Float.parseFloat(steps_run));
                gauge.setValue(progress);
            }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
