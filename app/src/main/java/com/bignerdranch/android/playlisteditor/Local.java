package com.bignerdranch.android.playlisteditor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Local extends AppCompatActivity {
TextView nameTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);

        nameTextView = (TextView) findViewById(R.id.playlist_name);
        Button createButton = (Button) findViewById(R.id.create_button);
        Button cancelButton = (Button) findViewById(R.id.cancel_button);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Local.this.finish();
            }
        });

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nameTextView.getText().length()==0) {
                    Toast.makeText(Local.this,"Please enter a name.",Toast.LENGTH_SHORT).show();
                    return;
                }

                DBManager dbManager = new DBManager(Local.this);
                dbManager.open();
                Cursor cursor = dbManager.fetch();

                String jsonContents = "{\"playlist\":[]}";
                dbManager.insert(nameTextView.getText().toString(),jsonContents);

                // create directory for the playlist
                String title = nameTextView.getText().toString();

                // zip file name is the same with .ZIP on the end.
                String zipFileName = title+".ZIP";
                String zipFilePath = getFilesDir().getPath() + "/" + zipFileName;

                File playlistDir = new File(getFilesDir(),title);
                if (!playlistDir.exists()) { // should be a new directory
                    playlistDir.mkdir();
                } else {
                    // TODO: handle error
                }

                // now we have an empty directory.  put an empty PLAYLIST.JSON file there.

                File jsonFile = new File(playlistDir,"PLAYLIST.JSON");
                if (!jsonFile.exists()) {  // it should not exist.
                    try {
                        // create the PLAYLIST.JSON file
                        jsonFile.createNewFile();
                        BufferedWriter buf = new BufferedWriter(new FileWriter(jsonFile,true));
                        buf.append(jsonContents);
                        buf.newLine();
                        buf.close();

                        // create zip file now
                        String[] s = new String[1];
                        s[0]=jsonFile.getAbsolutePath();
                        ZipManager.zip(s,zipFilePath);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    // TODO: handle error
                }
                Intent intent = new Intent(Local.this,PlayLists.class);
                Local.this.startActivity(intent);

            };
        });
    }
}
