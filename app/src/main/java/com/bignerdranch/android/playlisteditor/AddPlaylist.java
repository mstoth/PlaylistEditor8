package com.bignerdranch.android.playlisteditor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class AddPlaylist extends AppCompatActivity {
private ArrayList<String> playLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_playlist);
        Intent intent = getIntent();
        playLists = intent.getStringArrayListExtra("play_list");

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.ZipRecyclerView);
        MyListAdapter adapter = new MyListAdapter(getApplicationContext(),playLists);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }
}
