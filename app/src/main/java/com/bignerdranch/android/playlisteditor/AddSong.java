package com.bignerdranch.android.playlisteditor;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class AddSong extends AppCompatActivity implements SongsAdapter.ItemClickListener {
    private ArrayList<Song> songs;
    String[] hymns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_song);
        Intent intent = getIntent();
        JSONObject jobj;
        String jsonStr = intent.getStringExtra("jsonStr");

        try {
            jobj = new JSONObject(jsonStr);
            JSONArray f = jobj.getJSONArray("playlist");
            int c = f.length();
            for (int i = 0; i < c; i ++) {
                System.out.println(f.get(i));
            }

        } catch (JSONException e) {
            e.printStackTrace();
            return;
        }



        songs = new ArrayList<Song>();
        ArrayList<String> selectedSongs = new ArrayList<String>();

        // get the hymns
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
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.song_recycler_view);
        SongsAdapter adapter = new SongsAdapter(songs);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }


    @Override
    public void onItemClick(View view, int position) {
        System.out.println(position);
    }
}
