package com.bignerdranch.android.playlisteditor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.zip.ZipFile;

public class Edit extends AppCompatActivity {
private String title;
private String jsonStr,jsonStrFromFile;
ArrayList<Song> songs = new ArrayList<Song>();
ArrayList<Song> selectedSongs = new ArrayList<Song>();
private Playlist selectedPlaylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        SongsAdapter adapter;
        RecyclerView recyclerView = findViewById(R.id.songRecyclerView);

        setContentView(R.layout.activity_edit);
        EditText nameTextView = (EditText) findViewById(R.id.name_text_view);
        songs = new ArrayList<Song>();
        songs.clear();

        title = intent.getStringExtra("title");
        jsonStr = intent.getStringExtra("jsonStr");

        File zipFile = new File(getFilesDir(),title+".ZIP");
        if (zipFile.exists()) { // zip file should exist.
            try {
                ZipFile pl = new ZipFile(zipFile);
                File playlistDir = new File(getFilesDir(),title); // the directory we want to unzip into
                ZipManager.unzip(zipFile.getAbsolutePath(),playlistDir.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // zip file doesn't exist, this really shouldn't happen.
            // TODO: handle error
        }

        File playlistDir = new File(getFilesDir(),title);
        File jsonFile = new File(playlistDir,"PLAYLIST.JSON");

        try  {
            BufferedReader buf = new BufferedReader(new FileReader(jsonFile));
            String line, line1 = "";
            while ((line = buf.readLine()) != null)
                line1+=line;
            jsonStrFromFile = line1;

            JSONObject jsonObject = new JSONObject(jsonStr);
            System.out.println(jsonObject.toString());
            JSONArray playList = (JSONArray) jsonObject.getJSONArray("playlist");
            System.out.println(playList.toString());
            System.out.println(playList.length());
            System.out.println(jsonStrFromFile);
            if (playList.length()>0) {
                for (int i=0; i<playList.length(); i++) {
                    System.out.println(playList.get(i));
                    Object jo = playList.get(i);
                    System.out.println(jo.toString());
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        if (title != null) {
            nameTextView.setText(title);
        }

        nameTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String newTitle = String.valueOf(s);
                File pldir = new File(getFilesDir(),title); // playlist directory
                File npldir = new File(getFilesDir(),String.valueOf(s)); // new playlist directory
                pldir.renameTo(npldir);

                // setup database
                DBManager dbManager;
                dbManager = new DBManager(getApplicationContext());
                dbManager.open();
                Cursor cursor = dbManager.fetch();

                if (cursor.moveToFirst()){
                    do{
                        String titleFromDB = cursor.getString(cursor.getColumnIndex(DatabaseHelper.NAME));
                        if (titleFromDB == title) {
                            selectedPlaylist = new Playlist(title);
                            String jdata = cursor.getString(cursor.getColumnIndex(DatabaseHelper.JSON));
                            selectedPlaylist.setJsonString(jdata);

                        }

                    } while(cursor.moveToNext());
                }
                cursor.close();

            }
        });

        adapter = new SongsAdapter(songs,selectedSongs);
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

            Intent add_song = new Intent(getApplicationContext(),AddSong.class);
            add_song.putExtra("jsonStr",jsonStr);
            add_song.putExtra("title",title);
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
