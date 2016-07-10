package com.samir.popularmovies.model.review;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

public class ReviewDetail extends SugarRecord {

    @Expose
    public String content;

    @Expose
    public String author;

    public Long movieId;

}