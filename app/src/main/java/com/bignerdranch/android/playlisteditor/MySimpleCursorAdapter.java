package com.bignerdranch.android.playlisteditor;

import android.content.Context;
import android.database.Cursor;
import android.widget.SimpleCursorAdapter;

public class MySimpleCursorAdapter extends SimpleCursorAdapter {
    MyRecyclerViewAdapter.ItemClickListener mClickListener;
    public MySimpleCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
    }
    // allows clicks events to be caught
    public void setClickListener(MyRecyclerViewAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

}
