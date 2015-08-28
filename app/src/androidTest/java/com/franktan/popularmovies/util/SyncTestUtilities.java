package com.franktan.popularmovies.util;

import com.franktan.popularmovies.model.Genre;
import com.franktan.popularmovies.model.Movie;
import com.franktan.popularmovies.model.Review;
import com.franktan.popularmovies.model.Trailer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tan on 16/08/2015.
 */
public class SyncTestUtilities {
    public static Movie createMovieNo1 () {
        Movie movie = new Movie();
        movie.setBackdropPath("/kvXLZqY0Ngl1XSw7EaMQO0C1CCj.jpg");
        movie.setId(102899);
        movie.setOriginalLanguage("en");
        movie.setOriginalTitle("Ant-Man");
        movie.setOverview("Armed with the astonishing ability to shrink in scale but increase in strength, con-man Scott Lang must embrace his inner-hero and help his mentor, Dr. Hank Pym, protect the secret behind his spectacular Ant-Man suit from a new generation of towering threats. Against seemingly insurmountable obstacles, Pym and Lang must plan and pull off a heist that will save the world.");
        movie.setReleaseDate(1437091200000L); //2015-07-17
        movie.setPosterPath("/7SGGUiTE6oc2fh9MjIk5M00dsQd.jpg");
        movie.setPopularity(56.71);
        movie.setTitle("Ant-Man");
        movie.setVoteAverage(7.1);
        movie.setVoteCount(860);

        return  movie;
    }
    public static Movie createMovieNo20 () {
        Movie movie = new Movie();
        movie.setBackdropPath("/razvUuLkF7CX4XsLyj02ksC0ayy.jpg");
        movie.setId(260346);
        movie.setOriginalLanguage("en");
        movie.setOriginalTitle("Taken 3");
        movie.setOverview("Ex-government operative Bryan Mills finds his life is shattered when he's falsely accused of a murder that hits close to home. As he's pursued by a savvy police inspector, Mills employs his particular set of skills to track the real killer and exact his unique brand of justice.");
        movie.setReleaseDate(1420761600000L);  //2015-01-09
        movie.setPosterPath("/c2SSjUVYawDUnQ92bmTqsZsPEiB.jpg");
        movie.setPopularity(10.17);
        movie.setTitle("Taken 3");
        movie.setVoteAverage(6.2);
        movie.setVoteCount(873);

        return  movie;
    }

    public static Movie createMovieDetails () {
        return new Movie(76341,"Mad Max: Fury Road",createReviews(),createGenres(),createTrailers());
    }

    public static List<Review> createReviews () {
        List<Review> reviews = new ArrayList<Review>();
        Review review1 = new Review("55660928c3a3687ad7001db1", "Phileas Fogg", "Fabulous action movie. Lots of interesting characters. They don't make many movies like this. The whole movie from start to finish was entertaining I'm looking forward to seeing it again. I definitely recommend seeing it.", "http://j.mp/1HLTNzT");
        Review review2 = new Review("55732a53925141456e000639", "Andres Gomez", "Good action movie with a decent script for the genre. The photography is really good too but, in the end, it is quite repeating itself from beginning to end and the stormy OST is exhausting.", "http://j.mp/1dUnvpG");

        reviews.add(review1);
        reviews.add(review2);

        return reviews;
    }

    public static List<Genre> createGenres () {
        List<Genre> genres = new ArrayList<Genre>();

        Genre genre1 = new Genre(53, "Thriller");
        Genre genre2 = new Genre(28, "Action");
        Genre genre3 = new Genre(12, "Adventure");

        genres.add(genre1);
        genres.add(genre2);
        genres.add(genre3);

        return genres;
    }

    public static List<Trailer> createTrailers () {
        List<Trailer> trailers = new ArrayList<Trailer>();

        Trailer trailer1 = new Trailer("Trailers From Hell", "HD", "FRDdRto_3SA", "Featurette");
        Trailer trailer2 = new Trailer("Trailer 2", "HD", "jnsgdqppAYA", "Trailer");
        Trailer trailer3 = new Trailer("Official Trailer #1", "HD", "YWNWi-ZWL3c", "Trailer");

        trailers.add(trailer1);
        trailers.add(trailer2);
        trailers.add(trailer3);

        return trailers;
    }
}
