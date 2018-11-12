package com.example.bital_117.parsingjson;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    Geocoder geocoder;
    private Context mContext;
    private ArrayList<ListItem> mListItems;
    private List<Address>addresses = null;

    public Adapter(Context context, ArrayList<ListItem> listItems){
        mContext = context;
        mListItems = listItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v =LayoutInflater.from(mContext).inflate(R.layout.list_item,
                viewGroup , false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        ListItem currentItem = mListItems.get(position);

        String urlCamera = currentItem.getmUrlCamera();
        int mIdd = currentItem.getmId();
        String sid = Integer.toString(mIdd);
        double latitude = currentItem.getMlatitude();
        double longitude = currentItem.getMlongitude();

        geocoder = new Geocoder(mContext, Locale.getDefault());
        try {
            addresses = geocoder.getFromLocation(latitude,longitude,1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String alamat = addresses.get(0).getAddressLine(0);

        viewHolder.mTextViewLokasi.setText(alamat);
        viewHolder.mTextViewId.setText("Nomer : "+sid);

    }

    @Override
    public int getItemCount() {
        return mListItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextViewLokasi, mTextViewId, mTextViewLatitude, mTextViewLongitude;

        public ImageView mIconPlay;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewLokasi = itemView.findViewById(R.id.text_view_lokasi);
            mTextViewId = itemView.findViewById(R.id.text_view_id);
            mTextViewLatitude = itemView.findViewById(R.id.text_view_latitude);
            mTextViewLongitude = itemView.findViewById(R.id.text_view_longitude);
        }
    }
}
