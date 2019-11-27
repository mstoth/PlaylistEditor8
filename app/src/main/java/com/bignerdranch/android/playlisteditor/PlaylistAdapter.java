package com.bignerdranch.android.playlisteditor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View playlistView = inflater.inflate(viewType, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(context, playlistView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        final String title = mPlaylists.get(position).getName();
        final String jsonStr = mPlaylists.get(position).getJsonString();
        TextView textView = holder.nameTextView;
        holder.nameTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                TextView name = v.findViewById(R.id.playlist_name);
                System.out.println(name.getText());
                Intent intent = new Intent(holder.mContext,Edit.class);
                intent.putExtra("title",title);
                intent.putExtra("jsonStr",jsonStr);
                holder.mContext.startActivity(intent);
            }
        });
        textView.setText(title);
    }

    @Override
    public int getItemCount() {
        return mPlaylists.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.item_playlist;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nameTextView;
        private Context mContext;

        public ViewHolder(Context context,View itemView) {
            super(itemView);
            mContext = context;
            nameTextView = (TextView) itemView.findViewById(R.id.playlist_name);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                Playlist playlist = mPlaylists.get(pos);
                Toast.makeText(mContext,playlist.getName(),Toast.LENGTH_SHORT).show();
            }
        }
    }

    private List<Playlist> mPlaylists;

    public PlaylistAdapter(List<Playlist> playlists) {
        mPlaylists = playlists;
    }
}
