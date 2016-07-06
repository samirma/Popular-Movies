package com.samir.popularmovies.model.trailer;

public class Trailer
{
    private String id;

    private TraillerDetail[] results;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public TraillerDetail[] getResults ()
    {
        return results;
    }

    public void setResults (TraillerDetail[] results)
    {
        this.results = results;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", results = "+results+"]";
    }
}
