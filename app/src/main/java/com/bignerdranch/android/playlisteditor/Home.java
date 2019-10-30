package com.bignerdranch.android.playlisteditor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class Home extends Activity {
    JSch jsch;
    Session session;
    ArrayList<String> zipFiles = new ArrayList<String>();
    ArrayList<String> recordings = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);
        Toast.makeText(this,"Loading...",Toast.LENGTH_SHORT).show();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    jsch = new JSch();
                    session = null;
                    session = jsch.getSession("remoteuser", "192.168.1.4", 22);
                    session.setPassword("L\\9nW9TJaFZZuXHT");
                    java.util.Properties config = new java.util.Properties();
                    config.put("StrictHostKeyChecking", "no");
                    session.setConfig(config);
                    session.connect(10000);

                    // Now we are connected, get a list of the playlists on the organ
                    //val res = executeRemoteCommand(context,"ls /media/sda1/work","remoteuser","L\\9nW9TJaFZZuXHT","192.168.1.4")
                    ChannelExec sshChannel = (ChannelExec) session.openChannel("exec");
                    ByteArrayOutputStream oStream = new ByteArrayOutputStream();
                    sshChannel.setOutputStream(oStream);
                    sshChannel.setCommand("ls /media/sda1/work/");
                    sshChannel.connect();
                    try {
                        Thread.sleep(1000); // wait for the result
                        session.disconnect();
                        String res = oStream.toString();
                        // System.out.println(res);
                        // now create the list

                        byte[] bytesReceived = res.getBytes();
                        String songs = res.toString();
                        String delimiter = "\n";
                        String[] songArray;
                        songArray = songs.split(delimiter);
                        for (int i=0;i<songArray.length;i++) {
                            System.out.println(songArray[i]);
                            if (songArray[i].contains(".ZIP")) {
                                zipFiles.add(songArray[i]);
                            }
                            if (songArray[i].contains(".MID")) {
                                recordings.add(songArray[i]);
                            }
                        }

                    } catch (Exception e) {
                        System.out.println(e.getLocalizedMessage());
                    }



                } catch (JSchException e) {
                    e.printStackTrace();
                }

            }
        });
        thread.start();

        Button playListButton = (Button) findViewById(R.id.playlist_button);
        playListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,PlayLists.class);
                intent.putExtra("playlists",zipFiles);
                Home.this.startActivity(intent);
            }
        });

    }
}
