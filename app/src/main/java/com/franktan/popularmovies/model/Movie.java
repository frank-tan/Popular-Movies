package com.franktan.popularmovies.model;

import java.util.List;

/**
 * Created by tan on 16/08/2015.
 */
public class Movie {
    private String backdropPath;
    private int id;
    private String originalLanguage;
    private String originalTitle;
    private String overview;
    private long releaseDate;
    private String posterPath;
    private double popularity;
    private String title;
    private double voteAverage;
    private int voteCount;
    private List<Review> reviews;
    private List<Genre> genres;
    private List<Trailer> trailers;

    public Movie() {
    }

    public Movie(int id, String title, List<Review> reviews, List<Genre> genres, List<Trailer> trailers) {
        this.id = id;
        this.title = title;
        this.reviews = reviews;
        this.genres = genres;
        this.trailers = trailers;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Trailer> getTrailers() {
        return trailers;
    }

    public void setTrailers(List<Trailer> trailers) {
        this.trailers = trailers;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (id != movie.id) return false;
        if (releaseDate != movie.releaseDate) return false;
        if (Double.compare(movie.popularity, popularity) != 0) return false;
        if (Double.compare(movie.voteAverage, voteAverage) != 0) return false;
        if (voteCount != movie.voteCount) return false;
        if (backdropPath != null ? !backdropPath.equals(movie.backdropPath) : movie.backdropPath != null)
            return false;
        if (originalLanguage != null ? !originalLanguage.equals(movie.originalLanguage) : movie.originalLanguage != null)
            return false;
        if (originalTitle != null ? !originalTitle.equals(movie.originalTitle) : movie.originalTitle != null)
            return false;
        if (overview != null ? !overview.equals(movie.overview) : movie.overview != null)
            return false;
        if (posterPath != null ? !posterPath.equals(movie.posterPath) : movie.posterPath != null)
            return false;
        if (!title.equals(movie.title)) return false;
        if (reviews != null ? !reviews.equals(movie.reviews) : movie.reviews != null) return false;
        if (genres != null ? !genres.equals(movie.genres) : movie.genres != null) return false;
        return !(trailers != null ? !trailers.equals(movie.trailers) : movie.trailers != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = backdropPath != null ? backdropPath.hashCode() : 0;
        result = 31 * result + id;
        result = 31 * result + (originalLanguage != null ? originalLanguage.hashCode() : 0);
        result = 31 * result + (originalTitle != null ? originalTitle.hashCode() : 0);
        result = 31 * result + (overview != null ? overview.hashCode() : 0);
        result = 31 * result + (int) (releaseDate ^ (releaseDate >>> 32));
        result = 31 * result + (posterPath != null ? posterPath.hashCode() : 0);
        temp = Double.doubleToLongBits(popularity);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + title.hashCode();
        temp = Double.doubleToLongBits(voteAverage);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + voteCount;
        result = 31 * result + (reviews != null ? reviews.hashCode() : 0);
        result = 31 * result + (genres != null ? genres.hashCode() : 0);
        result = 31 * result + (trailers != null ? trailers.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "backdropPath='" + backdropPath + '\'' +
                ", id=" + id +
                ", originalLanguage='" + originalLanguage + '\'' +
                ", originalTitle='" + originalTitle + '\'' +
                ", overview='" + overview + '\'' +
                ", releaseDate=" + releaseDate +
                ", posterPath='" + posterPath + '\'' +
                ", popularity=" + popularity +
                ", title='" + title + '\'' +
                ", voteAverage=" + voteAverage +
                ", voteCount=" + voteCount +
                '}';
    }
}
