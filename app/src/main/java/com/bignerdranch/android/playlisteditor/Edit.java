package com.bignerdranch.android.playlisteditor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Edit extends AppCompatActivity {
private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<String> selectedHymns = new ArrayList<String>();
        Intent intent = getIntent();
        selectedHymns = intent.getStringArrayListExtra("hymns");
        setContentView(R.layout.activity_edit);
        TextView nameTextView = (TextView) findViewById(R.id.name_text_view);
        name = intent.getStringExtra("name");
        if (name != null) {
            nameTextView.setText(name);
        }
        Button hymn_button = (Button) findViewById(R.id.hymn_button);
        hymn_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Edit.this,Hymns.class);
                startActivity(intent);
            }
        });

    }
}
