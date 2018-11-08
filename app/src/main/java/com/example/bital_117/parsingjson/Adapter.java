package com.example.bital_117.parsingjson;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private Context mContext;
    private ArrayList<ListItem> mListItems;

    public Adapter(Context context, ArrayList<ListItem> listItems){
        mContext = context;
        mListItems = listItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextViewLokasi;
        public TextView mTextViewId;
        public ImageView mIconPlay;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewLokasi = itemView.findViewById(R.id.text_view_lokasi);
            mTextViewId = itemView.findViewById(R.id.text_view_id);
            
        }
    }
}
