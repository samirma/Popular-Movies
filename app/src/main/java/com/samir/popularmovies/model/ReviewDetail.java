package com.samir.popularmovies.model;

import com.orm.SugarRecord;

public class ReviewDetail extends SugarRecord {

    public String content;

    public String author;

    public Long movieId;

}