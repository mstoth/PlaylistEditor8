package com.bignerdranch.android.playlisteditor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class Edit extends AppCompatActivity {
private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<String> selectedHymns = new ArrayList<String>();
        ArrayList<Boolean> selected = new ArrayList<Boolean>();
        Intent intent = getIntent();
        selectedHymns = intent.getStringArrayListExtra("hymns");

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        setContentView(R.layout.activity_edit);
        EditText nameTextView = (EditText) findViewById(R.id.name_text_view);
        name = intent.getStringExtra("name");

        if (name != null) {
            nameTextView.setText(name);
        }

        selected = (ArrayList<Boolean>) intent.getSerializableExtra("selected");
        if (selected != null) {
            // now we have an boolean array of selected hymns from the Hymns activity
            System.out.println(selected.size());

        } else {
            System.out.println("No Selected");
        }
        Button hymn_button = (Button) findViewById(R.id.hymn_button);
        hymn_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Edit.this,Hymns.class);
                intent.putExtra("name",name);

                startActivity(intent);
            }
        });

    }
}
