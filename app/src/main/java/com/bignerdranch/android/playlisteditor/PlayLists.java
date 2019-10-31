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
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class PlayLists extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private DBManager dbManager;
    private SimpleCursorAdapter adapter;
    final String[] from = new String[] { DatabaseHelper._ID,
            DatabaseHelper.NAME, DatabaseHelper.JSON };
    final int[] to = new int[] { R.id.id, R.id.name, R.id.json };
    private ListView listView;
    private ArrayList<String> playLists;

//    @Override
//    protected void onResume(Bundle savedInstanceState) {
//        super.onResume();
//        Cursor cursor = dbManager.fetch();
//
//        adapter = new SimpleCursorAdapter(this, R.layout.activity_view_record, cursor, from, to, 0);
//        adapter.notifyDataSetChanged();
//
//        listView.setAdapter(adapter);
//    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        playLists = intent.getStringArrayListExtra("playlists");
        setContentView(R.layout.activity_play_lists);

        listView = (ListView) findViewById(R.id.list_view);

        // setup database
        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        adapter = new SimpleCursorAdapter(this, R.layout.activity_view_record, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);


//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.ZipRecyclerView);
//        MyListAdapter adapter = new MyListAdapter(playLists);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(adapter);
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
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.add_record) {
            AddPlaylistDialog d = new AddPlaylistDialog();
            d.show(getSupportFragmentManager(),"ADD PLAYLIST");

        }
        if (id == R.id.delete_record) {
            Intent delete_playlist = new Intent(this,Delete.class);
            // delete_playlist.putExtra("play_lists",playLists);
            startActivity(delete_playlist);
        }
        return super.onOptionsItemSelected(item);
    }


    public void deleteItem(int position, String item) {

            Toast.makeText(this, "You clicked on row number " + position, Toast.LENGTH_SHORT).show();

    }

}
