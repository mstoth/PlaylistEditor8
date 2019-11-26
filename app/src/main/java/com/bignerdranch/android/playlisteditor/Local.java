package com.bignerdranch.android.playlisteditor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

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

                Intent intent = new Intent(Local.this,PlayLists.class);
                Local.this.startActivity(intent);

            };
        });
    }
}
