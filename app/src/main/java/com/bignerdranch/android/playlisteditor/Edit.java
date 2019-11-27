package com.bignerdranch.android.playlisteditor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
        } catch(Exception e) {
            e.printStackTrace();
        }

        if (title != null) {
            nameTextView.setText(title);
        }



        Button hymn_button = (Button) findViewById(R.id.hymn_button);
        hymn_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Edit.this,Hymns.class);
                intent.putExtra("title",title);

                startActivity(intent);
            }
        });

        adapter = new SongsAdapter(songs);
        recyclerView = (RecyclerView) findViewById(R.id.songRecyclerView);
        recyclerView.setHasFixedSize(true);
        adapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


    }
}
