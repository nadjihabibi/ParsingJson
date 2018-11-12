package com.example.bital_117.parsingjson;

public class ListItem {
    private String mUrlCamera;
    private int mId;
    private double mlatitude;
    private double mlongitude;

    public ListItem(String mUrlCamera, int mId, double mlatitude, double mlongitude) {
        this.mUrlCamera = mUrlCamera;
        this.mId = mId;
        this.mlatitude = mlatitude;
        this.mlongitude = mlongitude;
    }

    public String getmUrlCamera() {
        return mUrlCamera;
    }

    public int getmId() {
        return mId;
    }

    public double getMlatitude() {
        return mlatitude;
    }

    public double getMlongitude() {
        return mlongitude;
    }

}
