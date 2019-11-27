package com.bignerdranch.android.playlisteditor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SongsAdapter extends
        RecyclerView.Adapter<SongsAdapter.ViewHolder> {

    private List<Song> mSongList;

    public SongsAdapter(List<Song> songs) {
        mSongList = songs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View songView = inflater.inflate(viewType,parent,false);
        ViewHolder viewHolder = new ViewHolder(context,songView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String title = mSongList.get(position).getName();
        String jsonStr = mSongList.get(position).getJSON();
        TextView textView = holder.nameTextView;
        textView.setText(title);
    }


    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Context mContext;
        public TextView nameTextView;

        public ViewHolder(Context context, View itemView) {
            super(itemView);
            mContext = context;
            nameTextView = (TextView) itemView.findViewById(R.id.songlist_name);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Song song = mSongList.get(position);
            }
        }
    }
}
