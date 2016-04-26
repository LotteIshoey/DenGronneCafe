package com.example.lotte.dengronnecafe;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

public class SongService extends Service {

    private final IBinder songBinder = new LocalBinder();
    MediaPlayer mp;
    int song_array[];
    int songnumber = 0;
    String song_names[] = {"Song 1", "Song 2", "Song 3"};


    @Override
    public void onCreate() {
        super.onCreate();

        song_array = new int[] {R.raw.song1, R.raw.song2, R.raw.song3};
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Toast.makeText(SongService.this, "The service is destoyed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startID){
        Toast.makeText(SongService.this, "The service has been started", Toast.LENGTH_SHORT).show();
        //return super.onStartCommand(intent, flags, startID);
        mp = MediaPlayer.create(this, song_array[songnumber]);
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return songBinder;
    }

    public class LocalBinder extends Binder {
        SongService getService(){
            return SongService.this;
        }
    }

    public void play_song(int index){
        stop_music();
        mp = MediaPlayer.create(this, song_array[index]);
        mp.start();
    }

    public void begin_player(int index){
    if(mp == null) {
        mp = MediaPlayer.create(this, song_array[index]);
    }
    if (!mp.isPlaying()) {
        play_song(songnumber);
    }
    else {
        mp.pause();
        }
    }

    public void stop_music(){
        if (mp != null){
            mp.stop();
            mp.release();
            mp = null;
        }
    }

    public void next_song(){

        if (mp != null) {
            songnumber++;
            if (songnumber < 3) {
                play_song(songnumber);

            } else {
                songnumber = 0;
                play_song(songnumber);
            }
        }
    }

    public void previous_song(){

        if (mp != null) {
            songnumber--;
            if (songnumber < 0) {
                songnumber = 2;
                play_song(songnumber);
            } else {
                play_song(songnumber);
            }
        }
    }

    public String getSongName (){
        String namesong = song_names[songnumber];
        Log.d("song", namesong);
        return namesong;
    }
}
