package com.bignerdranch.android.playlisteditor;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SongsViewHolder extends RecyclerView.ViewHolder {
    public TextView songTextView;

    public SongsViewHolder(@NonNull View itemView) {
        super(itemView);
        songTextView = (TextView) itemView.findViewById(R.id.songlist_name);

    }

    public void bindData(final Song viewModel) {
        songTextView.setText(viewModel.getName());
    }
}
