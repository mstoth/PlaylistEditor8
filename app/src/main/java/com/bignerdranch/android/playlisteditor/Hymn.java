package com.bignerdranch.android.playlisteditor;

import java.util.ArrayList;

public class Hymn {
    private String mTitle;
    public Hymn(String title) {
        mTitle = title;
    }
    public String getTitle() {
        return mTitle;
    }
    private String mJSON;
    public String getJSON() { return mJSON; }

    public void setJSON(String json) {
        mJSON = json;
    }

    public static ArrayList<Hymn> createHymnList(String[] hArray) {
        ArrayList<Hymn> result = new ArrayList<Hymn>();
        for (int i=0; i<hArray.length; i++) {
            result.add(new Hymn(hArray[i]));
        }
        return result;
    }
}
