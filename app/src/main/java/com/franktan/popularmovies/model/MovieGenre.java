package com.franktan.popularmovies.model;

/**
 * Created by tan on 22/09/2015.
 */
public class MovieGenre {
    long movieId;
    long genreId;

    public MovieGenre (long movieId, long genreId) {
        this.movieId = movieId;
        this.genreId = genreId;
    }

    public long getGenreId() {
        return genreId;
    }

    public void setGenreId(long genreId) {
        this.genreId = genreId;
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }
}
