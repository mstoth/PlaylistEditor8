package com.bignerdranch.android.playlisteditor;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;

public class AddSong extends AppCompatActivity {
    private ArrayList<Song> songs;
    String[] hymns;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_song);
        Intent intent = getIntent();
        String jsonStr = intent.getStringExtra("jsonStr");
        songs = new ArrayList<Song>();
        AssetManager manager = getApplicationContext().getAssets();
        try {
            hymns = manager.list("3C/hymns");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (hymns.length>0) {
            for (int i=0; i<hymns.length; i++) {
                String s = hymns[i];
                if (s.endsWith(".MID")) {
                    String ss = s.substring(0,s.length() - 4);
                    songs.add(new Song(ss));
                } else {
                    // ignore it, do nothing
                }
            }
        }
        String path = manager.toString();
        System.out.println(hymns.length);
//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.song_recycler_view);
//        SongsAdapter adapter = new SongsAdapter(songs);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(adapter);

    }

//    class RemoveSuffix
//    {
//        public  String removeSuffix(final String s, final String suffix)
//        {
//            if (s != null && suffix != null && s.endsWith(suffix)){
//                return s.substring(0, s.length() - suffix.length());
//            }
//            return s;
//        }
//
//        public  void main(String[] args) {
//            String s = "Java9";
//            System.out.println(removeSuffix(s, "9"));
//        }
//    }
//
}
