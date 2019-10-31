package com.bignerdranch.android.playlisteditor;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;

public class AddPlaylistDialog extends AppCompatDialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Add Playlist")
                .setPositiveButton("Import from Organ",new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(getContext(),Import.class);
                        AddPlaylistDialog.this.startActivity(intent);
                        // import
                    }
                })
                .setNegativeButton("Local", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // local
                        Intent intent = new Intent(getContext(),Local.class);
                        AddPlaylistDialog.this.startActivity(intent);
                    }
                })
                .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // cancel
                    }
                });
        return builder.create();

    }
}
