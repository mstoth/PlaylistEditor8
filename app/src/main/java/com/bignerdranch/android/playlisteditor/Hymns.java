package com.bignerdranch.android.playlisteditor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;

public class Hymns extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String[] names;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hymns);
        final AssetManager assets = this.getAssets();
        try {
            names = assets.list("3C");
            if (names.length==0) {
                System.out.println("NO HYMNS");
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
