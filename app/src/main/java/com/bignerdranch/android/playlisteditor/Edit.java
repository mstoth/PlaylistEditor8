package com.bignerdranch.android.playlisteditor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Edit extends AppCompatActivity {
private String title;
private String jsonStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<Song> songs = new ArrayList<Song>();
        Intent intent = getIntent();
        SongsAdapter adapter;
        RecyclerView recyclerView = findViewById(R.id.songRecyclerView);

        setContentView(R.layout.activity_edit);
        EditText nameTextView = (EditText) findViewById(R.id.name_text_view);
        songs = new ArrayList<Song>();
        songs.clear();

        title = intent.getStringExtra("title");
        jsonStr = intent.getStringExtra("jsonStr");
        try  {
            JSONObject jsonObject = new JSONObject(jsonStr);
            System.out.println(jsonObject.toString());
            JSONArray playList = (JSONArray) jsonObject.getJSONArray("playlist");
            System.out.println(playList.toString());
            System.out.println(playList.length());
            if (playList.length()>0) {
                JSONObject song = playList.getJSONObject(0);
            } else {
                // create sample song
                songs.add(new Song("New Song"));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        if (title != null) {
            nameTextView.setText(title);
        }


        adapter = new SongsAdapter(songs);
        recyclerView = (RecyclerView) findViewById(R.id.songRecyclerView);
        recyclerView.setHasFixedSize(true);
        adapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.add_record) {
            // AddPlaylistDialog d = new AddSongDialog();
            // d.show(getSupportFragmentManager(),"ADD SONG");
            Intent add_song = new Intent(getApplicationContext(),AddSong.class);
            startActivity(add_song);
        }
        if (id == R.id.delete_record) {
//            Intent delete_song = new Intent(this,DeleteSong.class);
//            startActivity(delete_song);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
