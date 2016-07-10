package com.samir.popularmovies.model.trailer;

import com.orm.dsl.Table;

@Table
public class TrailerDetail {

    public String site;

    public Long id;

    public String iso_639_1;

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