package com.franktan.popularmovies.model;

/**
 * Created by tan on 16/08/2015.
 */
public class Movie {
    private String backdropPath;
    private int movieDbId;
    private String originalLan;
    private String originalTitle;
    private String overview;
    private long releaseDate;
    private String posterPath;
    private double popularity;
    private String title;
    private boolean hasVideo;
    private double voteAverage;
    private int voteCount;

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public boolean isHasVideo() {
        return hasVideo;
    }

    public void setHasVideo(boolean hasVideo) {
        this.hasVideo = hasVideo;
    }

    public int getMovieDbId() {
        return movieDbId;
    }

    public void setMovieDbId(int movieDbId) {
        this.movieDbId = movieDbId;
    }

    public String getOriginalLan() {
        return originalLan;
    }

    public void setOriginalLan(String originalLan) {
        this.originalLan = originalLan;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public long getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(long releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }
}
