package com.example.lotte.dengronnecafe;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.OrientationEventListener;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Topsongs extends AppCompatActivity  implements SensorEventListener {

    //variables for orientation sensor
    OrientationEventListener myOrientationEventListener;
    String orientationText;

    //Variables for accelerometer
    float xAxis;
    float yAxis;
    float zAxis;
    float mLastX;
    float mLastY;
    float mLastZ;
    int songnumber = 0;

    int SHAKE_THRESHOLD = 800;

    //initilising a sensor manager
    SensorManager smanager;
    Sensor accelerometer;
    long lastUpdate;


    //Service variables
    SongService s;
    Boolean isBound = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topsongs);

       // After instantiating a connection with the Sensor Manager we need to select the sensor we want to monitor
        smanager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = smanager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);



        myOrientationEventListener = new OrientationEventListener(this, SensorManager.SENSOR_DELAY_NORMAL){
            @Override
            public void onOrientationChanged(int arg0) {
                orientationText = "Oritentationtext" + String.valueOf(arg0);
                Log.d("Orientation", orientationText);

               if (arg0 == -1) //the phone lies on the table
                {
                    s.begin_player(songnumber);
                    show_song();
                }
            }
        };

        if (myOrientationEventListener.canDetectOrientation()){
            //Log.d("Oritentation", "Can detect orientation");
            myOrientationEventListener.enable();
        }

    }
    protected void onDestroy() {
        super.onDestroy();
        myOrientationEventListener.disable();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Register a SensorEventListener
        smanager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        //service
        Intent intent = new Intent(this,SongService.class);
        bindService(intent, songConnection, Context.BIND_AUTO_CREATE);

    }

    @Override
    protected void onPause() {
        super.onPause();
        smanager.unregisterListener(this);

        //Service
        unbindService(songConnection);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        xAxis = event.values[0];
        yAxis = event.values[1];
        zAxis = event.values[2];

       // Log.d("xAxis", String.valueOf(event.values[0]));
        //Log.d("yAxis", String.valueOf(event.values[1]));
        //Log.d("zAxis", String.valueOf(event.values[2]));

        long curTime = System.currentTimeMillis();
        // only allow one update every 100ms.
        if ((curTime - lastUpdate) > 100) {
            long diffTime = (curTime - lastUpdate);
            lastUpdate = curTime;

            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            float speed = Math.abs(x+y+z - mLastX - mLastY - mLastZ) / diffTime * 10000;
            //Log.d("sensor", "shake detected w/ speed: " + speed);
            if (speed > SHAKE_THRESHOLD) {
                Toast.makeText(this, "shake detected w/ speed: " + speed, Toast.LENGTH_SHORT).show();
                s.next_song();
                show_song();
            }
            mLastX = x;
            mLastY = y;
            mLastZ = z;
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    public void start_song (View v) {
        s.begin_player(songnumber);
        show_song();
    }

    public void next_song (View v){
        s.next_song();
        show_song();
    }

    public void prev_song (View v){
        s.previous_song();
        show_song();
    }

    public void stop_song (View v){
            s.stop_music();
    }

    public void show_song(){
        String namesong = s.getSongName();
            TextView song = (TextView) findViewById(R.id.now_playing);
            song.setText(namesong);
    }

    //SERVICE METHODS
    @Override
    public void onStart(){
        super.onStart();
        explicitStart();
    }

    @Override
    public void onStop(){
        super.onStop();
        expliciteStop();
    }

    private ServiceConnection songConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            SongService.LocalBinder b = (SongService.LocalBinder) service;
            s = b.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            s = null;
            isBound = false;
        }
    };

    private void explicitStart() {
        //Explicitely start the song service
        Intent intent = new Intent(Topsongs.this, SongService.class);
        startService(intent);
    }

    private void expliciteStop() {
        stopService(new Intent(Topsongs.this, SongService.class));
    }
}
