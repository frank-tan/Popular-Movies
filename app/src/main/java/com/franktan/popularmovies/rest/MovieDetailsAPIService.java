package com.franktan.popularmovies.rest;

import com.franktan.popularmovies.model.Genre;
import com.franktan.popularmovies.model.Movie;
import com.franktan.popularmovies.model.Review;
import com.franktan.popularmovies.model.Trailer;
import com.franktan.popularmovies.util.Constants;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by tan on 28/08/2015.
 */
public class MovieDetailsAPIService {

    /**
     * The interface Retrofit takes to create REST api call
     */
    public interface MovieDetailsAPI {
        @GET("/3/movie/{id}")
        public Movie retrieveMovieDetails(@Path("id")int id);
    }

    /**
     * A custom deserializer which deserialize movie details json to the Movie class object
     */
    public static class MovieDeserializer implements JsonDeserializer<Movie> {
        @Override
        public Movie deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();

            JsonArray reviews = jsonObject.getAsJsonObject("reviews").getAsJsonArray("results");
            JsonArray trailers = jsonObject.getAsJsonObject("trailers").getAsJsonArray("youtube");
            JsonArray genres = jsonObject.getAsJsonArray("genres");

            Gson gson = new Gson();
            Type reviewListType = new TypeToken<List<Review>>() {}.getType();
            Type trailerListType = new TypeToken<List<Trailer>>() {}.getType();
            Type genresListType = new TypeToken<List<Genre>>() {}.getType();
            List<Review> reviewList = new Gson().fromJson(reviews, reviewListType);
            List<Trailer> trailerList = new Gson().fromJson(trailers, trailerListType);
            List<Genre> genreList = new Gson().fromJson(genres, genresListType);

            return new Movie(
                    jsonObject.get("id").getAsInt(),
                    jsonObject.get("title").getAsString(),
                    reviewList,
                    genreList,
                    trailerList
            );
        }
    }

    public static RestAdapter.Builder createRestAdapterBuilder (Gson gson) {

        return new RestAdapter.Builder()
                .setEndpoint(Constants.MOVIEDB_BASE_URL)
                .setConverter(new GsonConverter(gson));
    }

    public static Gson getGson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(Movie.class, new MovieDeserializer())
                .create();
    }

}
