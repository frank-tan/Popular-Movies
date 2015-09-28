package com.franktan.popularmovies.model;

/**
 * Created by tan on 31/08/2015.
 */
public enum SortCriterion {

    POPULARITY("popularity.desc"),
    RATING("rate_average.desc");

    private final  String sortBy;

    SortCriterion (String sortBy) {
        this.sortBy = sortBy;
    }

    public String getValue () {
        return sortBy;
    }
}
