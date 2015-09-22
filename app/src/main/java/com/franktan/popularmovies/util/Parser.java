package com.franktan.popularmovies.util;

import android.content.ContentValues;

import com.franktan.popularmovies.data.genre.GenreColumns;
import com.franktan.popularmovies.data.movie.MovieColumns;
import com.franktan.popularmovies.model.Genre;
import com.franktan.popularmovies.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
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
public class Parser {
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
            Long releaseDate = epochFromMovieDbDateString(releaseDateString);

            // convert popularity string to double with two decimal places
            double rawPopularity = Double.parseDouble(popularityString);
            DecimalFormat doubleFormat = new DecimalFormat("#.00");
            double popularity = Double.parseDouble(doubleFormat.format(rawPopularity));

            Movie movie = new Movie();
            movie.setBackdropPath(backdropPath);
            movie.setId(movieDbId);
            movie.setOriginalLanguage(originalLan);
            movie.setOriginalTitle(originalTitle);
            movie.setOverview(overview);
            movie.setReleaseDate(releaseDate);
            movie.setPosterPath(posterPath);
            movie.setPopularity(popularity);
            movie.setTitle(title);
            movie.setVoteAverage(voteAverage);
            movie.setVoteCount(voteCount);

            movieList.add(movie);
        }

        return movieList;
    }

    public static ContentValues[] contentValuesFromMovieList(List<Movie> movieList) {
        ContentValues[] movieContentValues = new ContentValues[movieList.size()];

        for (int i = 0; i < movieList.size(); i++) {
            Movie movie = movieList.get(i);
            ContentValues movieContentValue = new ContentValues();
            movieContentValue.put(MovieColumns.BACKDROP_PATH,    movie.getBackdropPath());
            movieContentValue.put(MovieColumns.MOVIE_MOVIEDB_ID, movie.getId());
            movieContentValue.put(MovieColumns.ORIGINAL_LAN,     movie.getOriginalLanguage());
            movieContentValue.put(MovieColumns.ORIGINAL_TITLE,   movie.getOriginalTitle());
            movieContentValue.put(MovieColumns.OVERVIEW,         movie.getOverview());
            movieContentValue.put(MovieColumns.RELEASE_DATE,     movie.getReleaseDate());
            movieContentValue.put(MovieColumns.POSTER_PATH,      movie.getPosterPath());
            movieContentValue.put(MovieColumns.POPULARITY,       movie.getPopularity());
            movieContentValue.put(MovieColumns.TITLE,            movie.getTitle());
            movieContentValue.put(MovieColumns.VOTE_AVERAGE,     movie.getVoteAverage());
            movieContentValue.put(MovieColumns.VOTE_COUNT,       movie.getVoteCount());
            movieContentValues[i] = movieContentValue;
        }
        return movieContentValues;
    }

    public static String humanDateStringFromMiliseconds(long miliseconds) {
        Date date = new Date(miliseconds);
        DateFormat format = new SimpleDateFormat("d MMM yyyy");
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        return format.format(date);
    }

    public static String movieDbDateStringFromMiliseconds(long miliseconds) {
        Date date = new Date(miliseconds);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        return format.format(date);
    }

    public static long epochFromMovieDbDateString (String movieDbDate) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date date = dateFormat.parse(movieDbDate);
        return date.getTime();
    }

    public static ContentValues[] contentValuesFromGenreList(List<Genre> genres) {
        ContentValues[] genreContentValues = new ContentValues[genres.size()];

        for (int i = 0; i < genres.size(); i++) {
            Genre genre = genres.get(i);
            ContentValues genreContentValue = new ContentValues();
            genreContentValue.put(GenreColumns.GENRE_MOVIEDB_ID,genre.getId());
            genreContentValue.put(GenreColumns.NAME,genre.getName());
            genreContentValues[i] = genreContentValue;
        }

        return genreContentValues;
    }
}
