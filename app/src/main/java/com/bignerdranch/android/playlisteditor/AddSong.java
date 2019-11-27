package com.bignerdranch.android.playlisteditor;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AddSong extends AppCompatActivity {
    private ArrayList<Song> songs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_song);
        Intent intent = getIntent();
        songs = (ArrayList<Song>) intent.getSerializableExtra("songs");

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.song_recycler_view);
        SongsAdapter adapter = new SongsAdapter(songs);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

}
