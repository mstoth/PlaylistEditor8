package com.bignerdranch.android.playlisteditor;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SimpleViewHolder extends RecyclerView.ViewHolder {
    private TextView playlistTextView;

    public SimpleViewHolder(@NonNull View itemView) {
        super(itemView);
        playlistTextView = (TextView) itemView.findViewById(R.id.playlist_title);

    }

    public void bindData(final Playlist viewModel) {
        playlistTextView.setText(viewModel.getName());
    }

}
