package com.franktan.popularmovies.util;

import com.franktan.popularmovies.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by tan on 16/08/2015.
 */
public class JsonParser {
    public static List<Movie> parseJson(String movieJsonString) throws JSONException, ParseException {
        JSONObject forecastJson = new JSONObject(movieJsonString);
        JSONArray movieArray = forecastJson.getJSONArray("results");
        int length = movieArray.length();

        List<Movie> movieList = new ArrayList<Movie>(length);

        for(int i = 0; i < length; i++) {
            JSONObject movieJsonObj = movieArray.getJSONObject(i);
            String backdropPath = movieJsonObj.getString("backdrop_path");
            int movieDbId = movieJsonObj.getInt("id");
            String originalLan = movieJsonObj.getString("original_language");
            String originalTitle = movieJsonObj.getString("original_title");
            String overview = movieJsonObj.getString("overview");
            String releaseDateString = movieJsonObj.getString("release_date");
            String posterPath = movieJsonObj.getString("poster_path");
            String popularityString = movieJsonObj.getString("popularity");
            String title = movieJsonObj.getString("title");
            boolean video = movieJsonObj.getBoolean("video");
            double voteAverage = movieJsonObj.getDouble("vote_average");
            int voteCount = movieJsonObj.getInt("vote_count");

            // convert date to epoch
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            Date date = dateFormat.parse(releaseDateString);
            Long releaseDate = date.getTime();

            // convert popularity string to double with two decimal places
            double rawPopularity = Double.parseDouble(popularityString);
            DecimalFormat doubleFormat = new DecimalFormat("#.00");
            double popularity = Double.parseDouble(doubleFormat.format(rawPopularity));

            Movie movie = new Movie();
            movie.setBackdropPath(backdropPath);
            movie.setMovieDbId(movieDbId);
            movie.setOriginalLan(originalLan);
            movie.setOriginalTitle(originalTitle);
            movie.setOverview(overview);
            movie.setReleaseDate(releaseDate);
            movie.setPosterPath(posterPath);
            movie.setPopularity(popularity);
            movie.setTitle(title);
            movie.setHasVideo(video);
            movie.setVoteAverage(voteAverage);
            movie.setVoteCount(voteCount);

            movieList.add(movie);
        }

        return movieList;
    }
}
