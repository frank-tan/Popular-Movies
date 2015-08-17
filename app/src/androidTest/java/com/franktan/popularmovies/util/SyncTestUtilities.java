package com.franktan.popularmovies.util;

import android.content.res.Resources;
import android.test.AndroidTestCase;

import com.franktan.popularmovies.model.Movie;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by tan on 16/08/2015.
 */
public class SyncTestUtilities {
    public static Movie createMovieNo1 () {
        Movie movie = new Movie();
        movie.setBackdropPath("/kvXLZqY0Ngl1XSw7EaMQO0C1CCj.jpg");
        movie.setOriginalLan("en");
        movie.setOriginalTitle("Ant-Man");
        movie.setOverview("Armed with the astonishing ability to shrink in scale but increase in strength, con-man Scott Lang must embrace his inner-hero and help his mentor, Dr. Hank Pym, protect the secret behind his spectacular Ant-Man suit from a new generation of towering threats. Against seemingly insurmountable obstacles, Pym and Lang must plan and pull off a heist that will save the world.");
        movie.setReleaseDate(1437091200L);
        movie.setPosterPath("/7SGGUiTE6oc2fh9MjIk5M00dsQd.jpg");
        movie.setPopularity(56.71);
        movie.setTitle("Ant-Man");
        movie.setHasVideo(false);
        movie.setVoteAverage(7.1);
        movie.setVoteCount(860);

        return  movie;
    }
    public static Movie createMovieNo20 () {
        Movie movie = new Movie();
        movie.setBackdropPath("/razvUuLkF7CX4XsLyj02ksC0ayy.jpg");
        movie.setOriginalLan("en");
        movie.setOriginalTitle("Taken 3");
        movie.setOverview("Ex-government operative Bryan Mills finds his life is shattered when he's falsely accused of a murder that hits close to home. As he's pursued by a savvy police inspector, Mills employs his particular set of skills to track the real killer and exact his unique brand of justice.");
        movie.setReleaseDate(1420761600L);
        movie.setPosterPath("/c2SSjUVYawDUnQ92bmTqsZsPEiB.jpg");
        movie.setPopularity(10.17);
        movie.setTitle("Taken 3");
        movie.setHasVideo(false);
        movie.setVoteAverage(6.2);
        movie.setVoteCount(873);

        return  movie;
    }

}
