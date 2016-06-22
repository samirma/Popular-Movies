package com.samir.popularmovies.model;


import android.os.Parcel;
import android.os.Parcelable;

public class MovieDB implements Parcelable {
    public String name;
    public String image;

    public MovieDB(Parcel parcel) {
        name = parcel.readString();
        image = parcel.readString();
    }

    public MovieDB() {

    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(image);
    }

    public final Parcelable.Creator<MovieDB> CREATOR = new Parcelable.Creator<MovieDB>() {
        @Override
        public MovieDB createFromParcel(Parcel parcel) {
            return new MovieDB(parcel);
        }

        @Override
        public MovieDB[] newArray(int i) {
            return new MovieDB[i];
        }

    };

    @Override
    public int describeContents() {
        return 0;
    }
}
