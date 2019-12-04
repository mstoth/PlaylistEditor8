package com.bignerdranch.android.playlisteditor;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class SongsAdapter extends
        RecyclerView.Adapter<SongsAdapter.ViewHolder>  {

    private List<Song> mSongList,mSelected;
    private  Context mContext;
    private ViewGroup mParent;
    private LayoutInflater mInflater;
    private View.OnClickListener v;
    public SongsAdapter(ArrayList<Song> songs,ArrayList<Song> sel) {
        mSongList = songs;
        mSelected = sel;
    }
    private SongsAdapter.ItemClickListener mClickListener;
    public ArrayList<Song> selectedSongs, selectedPreludes, selectedRecordings;

    SongsAdapter(Context context, List<Song> data) {
        this.mSongList = data;
        this.mInflater = LayoutInflater.from(context);
        selectedSongs = new ArrayList<Song>();
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


    // determines if song is selected
    boolean isSelected(Song s) {
        if (selectedSongs == null) {
            return false;
        }
        if (selectedSongs.size()>0) {
            for (int i = 0; i < selectedSongs.size(); i++) {
                if (s.getName() == selectedSongs.get(i).getName()) {
                    return true;
                }
            }
        }
        return false;
    }

    // removes song from selected list
    void removeSongFromSelected(Song s) {
        for (int i = 0; i < mSongList.size(); i++) {
            if (s.getName() == mSongList.get(i).getName()) {
                selectedSongs.remove(s);
                return;
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        String title = mSongList.get(position).getName();
        String jsonStr = mSongList.get(position).getJSON();
        TextView textView = holder.nameTextView;

        textView.setText(title);
        // seems the constructor is not called?  selectedSongs is null first time.
        if (selectedSongs == null) {
            selectedSongs = new ArrayList<Song>();
        }
        if (selectedRecordings == null) {
            selectedSongs = new ArrayList<Song>();
        }
        if (selectedPreludes == null) {
            selectedPreludes = new ArrayList<Song>();
        }
        textView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Song s = mSongList.get(position);
                TextView name = v.findViewById(R.id.songlist_name);
                if (isSelected(s)){
                    name.setBackgroundColor(Color.WHITE);

                    selectedSongs.remove(s);
                } else {
                    name.setBackgroundColor(Color.GREEN);
                    selectedSongs.add(s);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return mSongList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.item_songlist;
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

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
    // convenience method for getting data at click position
    Song getItem(int id) {
        return mSongList.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(SongsAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

}
