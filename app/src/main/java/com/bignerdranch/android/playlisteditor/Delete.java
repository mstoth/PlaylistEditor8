package com.bignerdranch.android.playlisteditor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class Delete extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {

    private ArrayList<String> playLists;
    private DBManager dbManager;
    private SimpleCursorAdapter adapter;
    final String[] from = new String[] { DatabaseHelper._ID,
            DatabaseHelper.NAME, DatabaseHelper.JSON };
    final int[] to = new int[] { R.id.id, R.id.name, R.id.json };
    private ListView listView;
    MyRecyclerViewAdapter rvadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
//        playLists = intent.getStringArrayListExtra("play_lists");
        setContentView(R.layout.activity_delete);

        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

//        adapter = new SimpleCursorAdapter(this, R.layout.activity_view_record, cursor, from, to, 0);
//        adapter.notifyDataSetChanged();

        playLists.clear();
        if (cursor.moveToFirst()){
            do{
                String data = cursor.getString(cursor.getColumnIndex(DatabaseHelper.NAME));
                playLists.add(data);
            } while(cursor.moveToNext());
        }
        cursor.close();

        rvadapter = new MyRecyclerViewAdapter(this,playLists);
        RecyclerView rv = findViewById(R.id.recyclerView4Delete);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rvadapter= new MyRecyclerViewAdapter(Delete.this,playLists);
        rvadapter.setClickListener(this);
        rv.setAdapter(rvadapter);
    }


    @Override
    public void onItemClick(View view, int position) {
        DBManager dbManager;
        SimpleCursorAdapter adapter;

        final String[] from = new String[] { DatabaseHelper._ID,
                DatabaseHelper.NAME, DatabaseHelper.JSON };
        final int[] to = new int[] { R.id.id, R.id.name, R.id.json };
        String name = playLists.get(position);
        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetchWithName(name);
        dbManager.delete(cursor.getInt(0));
    }
}
