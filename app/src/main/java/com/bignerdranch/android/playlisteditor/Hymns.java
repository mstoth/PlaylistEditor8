package com.bignerdranch.android.playlisteditor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Hymns extends AppCompatActivity {
ArrayList<Hymn> hymns;
String[] names;
ArrayList<Boolean> selected;
String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_hymns);
        final AssetManager assets = this.getAssets();
        try {
            names = assets.list("3C/hymns");
            if (names.length==0) {
                System.out.println("NO HYMNS");
            }

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        selected = (ArrayList<Boolean>) getIntent().getSerializableExtra("selected");

        String title = getIntent().getStringExtra("name");
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.hymn_recycler_view);
        hymns = Hymn.createHymnList(names);
        Button addHymnsButton = (Button) findViewById(R.id.add_hymns_button);

        addHymnsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Hymns.this,Edit.class);
                intent.putExtra("selected",selected);
                startActivity(intent);
            }
        });

        if (selected == null) {
            // initially nothing selected
            selected = new ArrayList<Boolean>();
            for (int i = 0; i < hymns.size(); i++) {
                selected.add(false);
            }
        }

        HymnsAdapter adapter = new HymnsAdapter(hymns,selected);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            @Override
            public void onTouchEvent(RecyclerView recycler, MotionEvent event) {
                // Handle on touch events here
                System.out.println("touch event.");
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }

            @Override
            public boolean onInterceptTouchEvent(RecyclerView recycler, MotionEvent event) {
                return false;
            }

        });
    }
}
