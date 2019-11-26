package com.bignerdranch.android.playlisteditor;

import java.util.ArrayList;

public class Playlist {
    private String jsonString;
    private ArrayList<Song> mSongs;
    private String mName;
    public Playlist(String name) {
        mName = name;
        mSongs = new ArrayList<Song>();
    }

    public void setJsonString(String s) {
        jsonString = s;
    }

    public String getJsonString() {
        return jsonString;
    }

    public ArrayList<Song> getSongs() {
        return mSongs;
    }

    public String getName() {
        return mName;
    }

    public void setName(String n) {
        mName = n;
    }

    public void addSong(Song s) {
        mSongs.add(s);
    }

    public void removeSong(Song s) {
        for (int i = 0; i < mSongs.size(); i++ ) {
            Song ss = mSongs.get(i);
            if (s.getName() == ss.getName()) {
                mSongs.remove(ss);
            }
        }
    }
}
