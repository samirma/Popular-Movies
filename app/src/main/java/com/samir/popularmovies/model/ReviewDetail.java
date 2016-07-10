package com.samir.popularmovies.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "ReviewDetail")
public class ReviewDetail extends Model {

    @Column(name = "content")
    public String content;

    @Column(name = "author")
    public String author;

    @Column(name = "movieId")
    public Long movieId;

}