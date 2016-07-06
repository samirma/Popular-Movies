package com.samir.popularmovies.model.trailer;

public class TraillerDetail {

    public String site;

    public String id;

    public String iso_639_1;

    public String name;

    public String type;

    public String key;

    public String iso_3166_1;

    public String size;

    public boolean isTrailer(){
        final boolean isTrailer = "Trailer".equals(type);
        return isTrailer;
    }

}