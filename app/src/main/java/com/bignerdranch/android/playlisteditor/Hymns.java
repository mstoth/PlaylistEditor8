package com.bignerdranch.android.playlisteditor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;

public class Hymns extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hymns);
        final AssetManager assets = this.getAssets();
        try {
            final String[] names = assets.list("3C");
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
