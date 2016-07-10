package com.samir.popularmovies.model.trailer;

public class Trailer
{
    private String id;

    private TrailerDetail[] results;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public TrailerDetail[] getResults ()
    {
        return results;
    }

    public void setResults (TrailerDetail[] results)
    {
        this.results = results;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", results = "+results+"]";
    }
}
