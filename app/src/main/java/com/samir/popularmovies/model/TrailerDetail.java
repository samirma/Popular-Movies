package com.samir.popularmovies.model;

import com.orm.SugarRecord;

public class TrailerDetail extends SugarRecord {

    public String name;

    public String type;

    public String key;

    public String iso_3166_1;

    public String size;

    public Long movieId;

    public boolean isTrailer(){
        final boolean isTrailer = "Trailer".equals(type);
        return isTrailer;
    }

}