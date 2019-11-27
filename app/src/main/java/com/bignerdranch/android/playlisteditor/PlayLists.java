package com.bignerdranch.android.playlisteditor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

public class PlayLists extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {
    private RecyclerView recyclerView;
    private PlaylistAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private DBManager dbManager;
    private PlaylistAdapter adapter;
    final String[] from = new String[] { DatabaseHelper._ID,
            DatabaseHelper.NAME, DatabaseHelper.JSON };
    final int[] to = new int[] { R.id.id, R.id.name, R.id.json };
    private RecyclerView rcycView;
    private ArrayList<Playlist> playLists;
    private ArrayList<String> jsonData;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        playLists = new ArrayList<Playlist>();
        jsonData = new ArrayList<String>();
        setContentView(R.layout.activity_play_lists);


        // setup database
        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        playLists.clear();
        if (cursor.moveToFirst()){
            do{
                String data = cursor.getString(cursor.getColumnIndex(DatabaseHelper.NAME));
                Playlist p = new Playlist(data);
                String jdata = cursor.getString(cursor.getColumnIndex(DatabaseHelper.JSON));
                    p.setJsonString(jdata);
                playLists.add(p);
            } while(cursor.moveToNext());
        }
        cursor.close();

        adapter = new PlaylistAdapter(playLists);
        rcycView = (RecyclerView) findViewById(R.id.Zip2RecyclerView);
        rcycView.setHasFixedSize(true);
        adapter.notifyDataSetChanged();
        rcycView.setLayoutManager(new LinearLayoutManager(this));
        rcycView.setAdapter(adapter);

    }


    public void reload() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public void onItemClick(View view, int position) {
        DBManager dbManager;
        SimpleCursorAdapter adapter;
        Playlist p = playLists.get(position);
        String name = p.getName();
        Intent intent = new Intent(PlayLists.this,Edit.class);
        intent.putExtra("name", name);
        startActivity(intent);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.add_record) {
            AddPlaylistDialog d = new AddPlaylistDialog();
            d.show(getSupportFragmentManager(),"ADD PLAYLIST");

        }
        if (id == R.id.delete_record) {
            Intent delete_playlist = new Intent(this,Delete.class);
            startActivity(delete_playlist);
        }
        return super.onOptionsItemSelected(item);
    }



}
