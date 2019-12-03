package com.bignerdranch.android.playlisteditor;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class EditSong extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_song);

        EditText nameTextView = this.findViewById(R.id.song_name_edit_text);
        EditText tempoTextView = this.findViewById(R.id.tempo_adjust_edit_text);
        EditText numberOfVersesTextView = this.findViewById(R.id.num_verses_edit_text);
        ToggleButton introToggleButton = this.findViewById(R.id.introToggle);

        Button okButton = this.findViewById(R.id.ok_button);
        Button cancelButton = this.findViewById(R.id.cancel_button);

        Intent intent = getIntent();
        String songTitle = intent.getStringExtra("title");
        String jsonStr = intent.getStringExtra("jsonStr");


        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        nameTextView.setText(songTitle);
        try {
            // set up default values
            JSONObject jsonObj = new JSONObject(jsonStr);
            String fileName = jsonObj.getString("file");
            String jsonTempo = jsonObj.getString("tempo_adjust");
            if (jsonTempo=="0") {
                jsonTempo = "100";
            }
            int jsonVerses = jsonObj.getInt("verses");
            if (jsonVerses > 0) {
                numberOfVersesTextView.setText(String.valueOf(jsonVerses));
            } else {
                numberOfVersesTextView.setText("1");
            }
            tempoTextView.setText(jsonTempo);
            nameTextView.setText(fileName);

            // configure the spinner (not sure we want a spinner)
//            Spinner spinner = (Spinner) findViewById(R.id.spinner);
//            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.num_verses, android.R.layout.simple_spinner_item);
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            spinner.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
