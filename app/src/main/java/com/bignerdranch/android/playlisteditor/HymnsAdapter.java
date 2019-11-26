package com.bignerdranch.android.playlisteditor;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class HymnsAdapter extends
        RecyclerView.Adapter<HymnsAdapter.ViewHolder> {


    private List<Hymn> mHymns;

    private List<Boolean> selected;

    public HymnsAdapter(List<Hymn> hymns, List<Boolean> sel) {
        mHymns = hymns;
        selected = sel;
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
         TextView titleTextView;
         LinearLayout hymnLayout;
         public ViewHolder(View itemView) {
             super(itemView);
             hymnLayout = (LinearLayout) itemView.findViewById(R.id.hymn_layout);
             titleTextView = (TextView) itemView.findViewById(R.id.hymn_title);
         }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View hymnView = inflater.inflate(R.layout.item_hymn,parent,false);
        ViewHolder viewHolder = new ViewHolder(hymnView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Hymn hymn = mHymns.get(position);
        TextView textView = holder.titleTextView;
        textView.setText(hymn.getTitle());
//        if (selected.get(position)) {
//            holder.titleTextView.setBackgroundColor(Color.GREEN);
//        } else {
//            holder.titleTextView.setBackgroundColor(Color.WHITE);
//        }

        holder.hymnLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selected.get(position)) {
                    selected.set(position,false);
                    removeHymnFromPlaylist(hymn.getTitle());
                    v.setBackgroundColor(Color.WHITE);
                } else {
                    selected.set(position,true);
                    addHymnToPlaylist(hymn.getTitle(),hymn.getJSON());
                    v.setBackgroundColor(Color.GREEN);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mHymns.size();
    }

    public void addHymnToPlaylist(String title,String jsonStr) {
        for (int i=0; i<mHymns.size(); i++) {
            if (mHymns.get(i).getTitle() == title) {
                selected.set(i,true);
            }
        }
    }

    public void removeHymnFromPlaylist(String title) {
        for (int i=0; i<mHymns.size(); i++) {
            if (mHymns.get(i).getTitle() == title) {
                selected.set(i,false);
            }
        }
    }
}


