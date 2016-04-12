package com.example.lotte.dengronnecafe;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.OrientationEventListener;
import android.view.View;
import android.widget.Toast;

public class Topsongs extends AppCompatActivity  implements SensorEventListener {

    MediaPlayer mp1;
    MediaPlayer mp2;
    MediaPlayer mp3;
    MediaPlayer mp;

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
    int song_array[];

    int SHAKE_THRESHOLD = 2000;

    //initilising a sensor manager
    SensorManager smanager;
    Sensor accelerometer;
    long lastUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topsongs);
        mp1 = MediaPlayer.create(this, R.raw.song1);
        mp2 = MediaPlayer.create(this, R.raw.song2);
        mp3 = MediaPlayer.create(this, R.raw.song3);
        song_array = new int[] {R.raw.song1, R.raw.song2, R.raw.song3};
        mp = MediaPlayer.create(this, song_array[songnumber]);

       // After instantiating a connection with the Sensor Manager we need to select the sensor we want to monitor
        smanager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = smanager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);



        myOrientationEventListener = new OrientationEventListener(this, SensorManager.SENSOR_DELAY_NORMAL){
            @Override
            public void onOrientationChanged(int arg0) {
                orientationText = "Oritentationtext" + String.valueOf(arg0);
                Log.d("Orientation", orientationText);

               /* if (arg0 > 70 && arg0 < 100)
                {
                    mp1.start();
                }
                if (arg0 > 0 && arg0 < 69)
                {
                    if(mp1.isPlaying()) {
                        mp1.pause();
                   }
                }
*/
            }};
        if (myOrientationEventListener.canDetectOrientation()){
            Log.d("Oritentation", "Can detect orientation");
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
    }

    @Override
    protected void onPause() {
        super.onPause();
        smanager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float NOISE = (float) 2.0;
        xAxis = event.values[0];
        yAxis = event.values[1];
        zAxis = event.values[2];
        long actualTime = System.currentTimeMillis();

        Log.d("xAxis", String.valueOf(event.values[0]));
        Log.d("yAxis", String.valueOf(event.values[1]));
        Log.d("zAxis", String.valueOf(event.values[2]));

        long curTime = System.currentTimeMillis();
        // only allow one update every 100ms.
        if ((curTime - lastUpdate) > 100) {
            long diffTime = (curTime - lastUpdate);
            lastUpdate = curTime;

            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            float speed = Math.abs(x+y+z - mLastX - mLastY - mLastZ) / diffTime * 10000;

            if (speed > SHAKE_THRESHOLD) {
                Log.d("sensor", "shake detected w/ speed: " + speed);
                Toast.makeText(this, "shake detected w/ speed: " + speed, Toast.LENGTH_SHORT).show();
                if(mp.isPlaying())
                {
                    mp.stop();
                }
                next_song();
            }
            mLastX = x;
            mLastY = y;
            mLastZ = z;
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void start_song (View v){
        play_song(songnumber);

    }

    public void stop_music (){
        if (mp != null){
            mp.stop();
            mp.release();
            mp = null;
        }
    }

    public void next_song(){
        songnumber++;
        if (songnumber < 3){
            play_song(songnumber);

        }
        else
        {
            songnumber = 0;
            play_song(songnumber);
        }
    }

    public void play_song(int index){
        stop_music();
        mp = MediaPlayer.create(this,song_array[index]);
        mp.start();
    }

}
