package com.bignerdranch.android.playlisteditor;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class AddSong extends AppCompatActivity implements SongsAdapter.ItemClickListener {
    private ArrayList<Song> songs,performances,selectedHymns,selectedPerformances,selectedRecordings;
    private RecyclerView recyclerView;
    String[] hymns;
    String[] preludes;
    SongsAdapter adapter;
    String title, jsonStr;
    boolean selectingHymns,selectingPerformances,selectingRecordings;
    Button performancesButton;
    Button hymnButton;
    Button recordingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_song);
        selectingHymns = true;
        selectingPerformances = false;
        selectingRecordings = false;
        performancesButton = findViewById(R.id.add_performance);
        hymnButton = findViewById(R.id.add_hymn_button);
        recordingButton = findViewById(R.id.add_recording);

        // HYMN BUTTON
        hymnButton.setBackgroundColor(Color.GREEN);
        hymnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // switch to hymns
                if (selectingPerformances) {
                    selectedPerformances = adapter.selectedSongs;
                    OutputStream out = null;
                    if (selectedPerformances.size()>0) {
                        for (int i=0; i<selectedPerformances.size(); i++) {
                            Song ss = selectedPerformances.get(i);
                            AssetManager m = getAssets();
                            try {
                                AssetFileDescriptor fd = m.openFd("3C/preludes/"+ss.getName()+".MID");
                                String destDir = getFilesDir().getAbsolutePath() + "/" + title;
                                InputStream in = m.open("3C/preludes/"+ss.getName()+".MID");
                                File outFile = new File(getFilesDir(),title+"/"+ss.getName()+".MID");
                                out = new FileOutputStream(outFile);
                                copyFile(in,out);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    selectingPerformances = false;
                    selectingHymns = true;
                    v.setBackgroundColor(Color.GREEN);
                    performancesButton.setBackgroundColor(Color.LTGRAY);
                }
                adapter = new SongsAdapter(songs,selectedHymns);
                adapter.setClickListener(AddSong.this);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });

        // CANCEL BUTTON
        Button cancelButton = findViewById(R.id.cancel_add_song);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // OK BUTTON
        Button okButton = findViewById(R.id.ok_add_song);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // copy files to directory
                AssetManager manager = getApplicationContext().getAssets();
                selectedHymns = adapter.selectedSongs;
                for (int i=0;i<selectedHymns.size();i++) {
                    System.out.println(selectedHymns.get(i).getName());
                }
//                File source = manager.openFd();
//
//                String destinationPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/TongueTwister/tt_1A.3gp";
//                File destination = new File(destinationPath);
//                try
//                {
//                    FileUtils.copyFile(source, destination);
//                }
//                catch (IOException e)
//                {
//                    e.printStackTrace();
//                }
            }
        });

        // RECORDING BUTTON
        recordingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // switch to recordings
                if (selectingHymns) {
                    selectedHymns = adapter.selectedSongs;
                    OutputStream out = null;
                    if (selectedHymns.size()>0) {
                        for (int i=0; i<selectedHymns.size(); i++) {
                            Song ss = selectedHymns.get(i);
                            AssetManager m = getAssets();
                            try {
                                AssetFileDescriptor fd = m.openFd("3C/hymns/"+ss.getName()+".MID");
                                String destDir = getFilesDir().getAbsolutePath() + "/" + title;
                                InputStream in = m.open("3C/hymns/"+ss.getName()+".MID");
                                File outFile = new File(getFilesDir(),title+"/"+ss.getName()+".MID");
                                out = new FileOutputStream(outFile);
                                copyFile(in,out);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    selectingHymns = false;
                    selectingRecordings = true;
                    v.setBackgroundColor(Color.GREEN);
                    hymnButton.setBackgroundColor(Color.LTGRAY);
                }
            }
        });

        // PERFORMANCE BUTTON
        performancesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // switch to performances
                if (selectingHymns) {
                    selectedHymns = adapter.selectedSongs;
                    OutputStream out = null;
                    if (selectedHymns.size()>0) {
                        for (int i=0; i<selectedHymns.size(); i++) {
                            Song ss = selectedHymns.get(i);
                            AssetManager m = getAssets();
                            try {
                                AssetFileDescriptor fd = m.openFd("3C/hymns/"+ss.getName()+".MID");
                                String destDir = getFilesDir().getAbsolutePath() + "/" + title;
                                InputStream in = m.open("3C/hymns/"+ss.getName()+".MID");
                                File outFile = new File(getFilesDir(),title+"/"+ss.getName()+".MID");
                                out = new FileOutputStream(outFile);
                                copyFile(in,out);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    selectingHymns = false;
                    selectingPerformances = true;
                    v.setBackgroundColor(Color.GREEN);
                    hymnButton.setBackgroundColor(Color.LTGRAY);
                }
                adapter = new SongsAdapter(performances,selectedPerformances);
                adapter.setClickListener(AddSong.this);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });


        Intent intent = getIntent();
        JSONObject jobj;
        jsonStr = intent.getStringExtra("jsonStr");
        title = intent.getStringExtra("title");
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

        performances = new ArrayList<Song>();

        // get the performances
        try {
            preludes = manager.list("3C/preludes");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (preludes.length > 0) {
            for (int i=0; i<preludes.length; i++) {
                String s = preludes[i];
                if (s.endsWith(".MID")) {
                    String ss = s.substring(0,s.length()-4);
                    performances.add(new Song(ss));
                }
            }
        }

        String path = manager.toString();
        System.out.println(hymns.length);
        recyclerView = (RecyclerView) findViewById(R.id.song_recycler_view);
        adapter = new SongsAdapter(songs,selectedHymns);
        adapter.setClickListener(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }



    @Override
    public void onItemClick(View view, int position) {
        System.out.println(position);
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }
}

