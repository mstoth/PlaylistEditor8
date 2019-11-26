package com.bignerdranch.android.playlisteditor;

public class Song {
    private String mName;
    private int mTempoAdjust;
    private int mNumVerses;
    private int mVolume;

    public Song(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public void setTempoAdjust(int v) {
        mTempoAdjust = v;
    }

    public void setNumVerses(int n) {
        mNumVerses = n;
    }

    public void setVolume(int v) {
        mVolume = v;
    }

    public String getJSON() {
        return "{\"file\":\"" + mName + "\",\"tempo_adjust\":" + mTempoAdjust + ",\"verses\":" + mNumVerses +
                ",\"volume\":" + mVolume + "}";
    }

}
