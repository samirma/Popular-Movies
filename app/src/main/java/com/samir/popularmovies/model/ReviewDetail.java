package com.samir.popularmovies.model;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

public class ReviewDetail extends SugarRecord {

    public String content;

    public String author;

    public Long movieId;

}