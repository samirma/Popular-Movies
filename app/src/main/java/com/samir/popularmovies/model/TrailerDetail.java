package com.samir.popularmovies.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "TrailerDetail")
public class TrailerDetail extends Model {

    public String site;

    public String iso_639_1;
    @Column(name = "name")
    public String name;

    @Column(name = "type")
    public String type;
    @Column(name = "key")
    public String key;

    public String iso_3166_1;

    public String size;
    @Column(name = "movieId")
    public Long movieId;

    public boolean isTrailer(){
        final boolean isTrailer = "Trailer".equals(type);
        return isTrailer;
    }

}