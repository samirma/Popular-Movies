package com.samir.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;


@Table(name = "Movie")
public class Movie extends Model implements Parcelable {

    @Column(name = "idJson")
    public Long id;

    @Column(name = "vote_average")
    public String vote_average;

    @Column(name = "backdrop_path")
    public String backdrop_path;

    public String adult;

    @Column(name = "title")
    public String title;

    @Column(name = "overview")
    public String overview;

    public String original_language;

    public String[] genre_ids;

    @Column(name = "release_date")
    public String release_date;

    @Column(name = "original_title")
    public String original_title;

    @Column(name = "vote_count")
    public String vote_count;

    @Column(name = "poster_path")
    public String poster_path;

    @Column(name = "video")
    public String video;

    @Column(name = "popularity")
    public String popularity;

    @Column(name = "isFavorited")
    public Boolean isFavorited = false;


    public Movie() {
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
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
        this.id = (Long) in.readValue(Long.class.getClassLoader());
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