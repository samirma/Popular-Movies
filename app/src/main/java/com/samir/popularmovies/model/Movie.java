package com.samir.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;


public class Movie extends SugarRecord implements Parcelable {

    public String vote_average;

    public String backdrop_path;

    public String adult;

    public String title;

    public String overview;

    public String original_language;

    public String[] genre_ids;

    public String release_date;

    public String original_title;

    public String vote_count;

    public String poster_path;

    public String video;

    public String popularity;

    public Boolean isFavorited = false;


    public Movie() {
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(getId());
        dest.writeString(this.vote_average);
        dest.writeString(this.backdrop_path);
        dest.writeString(this.adult);
        dest.writeString(this.title);
        dest.writeString(this.overview);
        dest.writeString(this.original_language);
        dest.writeStringArray(this.genre_ids);
        dest.writeString(this.release_date);
        dest.writeString(this.original_title);
        dest.writeString(this.vote_count);
        dest.writeString(this.poster_path);
        dest.writeString(this.video);
        dest.writeString(this.popularity);
        dest.writeValue(this.isFavorited);
    }

    protected Movie(Parcel in) {
        setId((Long) in.readValue(Long.class.getClassLoader()));
        this.vote_average = in.readString();
        this.backdrop_path = in.readString();
        this.adult = in.readString();
        this.title = in.readString();
        this.overview = in.readString();
        this.original_language = in.readString();
        this.genre_ids = in.createStringArray();
        this.release_date = in.readString();
        this.original_title = in.readString();
        this.vote_count = in.readString();
        this.poster_path = in.readString();
        this.video = in.readString();
        this.popularity = in.readString();
        this.isFavorited = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}