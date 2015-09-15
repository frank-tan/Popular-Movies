package com.franktan.popularmovies.model;

/**
 * Created by tan on 15/09/2015.
 */
public enum MovieGroup {
    POPULAR ("Popular"),
    TOP_RATED ("Top Rated"),
    FAVORITE ("Favourite");

    String title;

    MovieGroup(String title) {
        this.title = title;
    }

    public String getTitle (){
        return this.title;
    }
}
