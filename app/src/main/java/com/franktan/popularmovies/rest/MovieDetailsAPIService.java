package com.franktan.popularmovies.rest;

import android.content.Context;

import com.franktan.popularmovies.model.Genre;
import com.franktan.popularmovies.model.Movie;
import com.franktan.popularmovies.model.Review;
import com.franktan.popularmovies.model.Trailer;
import com.franktan.popularmovies.util.Constants;
import com.franktan.popularmovies.util.Utilities;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.RestAdapter;
import retrofit.client.Client;
import retrofit.converter.GsonConverter;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.QueryMap;

/**
 * Created by tan on 28/08/2015.
 */
public class MovieDetailsAPIService {

    /**
     * The interface Retrofit takes to create REST api call
     */
    public interface MovieDetailsAPI {
        @GET("/3/movie/{id}")
        public Movie retrieveMovieDetails(@Path("id")long id, @QueryMap Map<String, String> options);
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

    /**
     * Create a Rest adapter builder with base end point and a given custom gson
     * @param client allow caller injecting a custom client (for testing purpose). Leave it null for real API call.
     * @return
     */
    public static RestAdapter createRestAdapter(Client client) {

        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(Constants.MOVIEDB_BASE_URL)
                .setConverter(new GsonConverter(movieDetailsCustomGson()));

        if(client != null) {
            return builder.setClient(client).build();
        }
        return builder.build();
    }

    /**
     * Create a custom gson with our custom deserializer
     * @return
     */
    public static Gson movieDetailsCustomGson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(Movie.class, new MovieDeserializer())
                .create();
    }

    /**
     * Create url parameters needed for the API call
     * @param context
     * @return
     */
    public static Map<String,String> movieDetailsURLParameters(Context context) {
        Map<String, String> parameters = new HashMap<String, String>(2);
        parameters.put("api_key", Utilities.getApiKey(context));
        parameters.put("append_to_response", "reviews,trailers");
        return parameters;
    }

    /**
     * Call MovieDB and retrieve details of the movie identified by movieId
     * @param context
     * @param movieId
     * @param client for testing purpose, you can provide a mock client. Leave it to null for real MovieDB api call
     * @return
     */
    public static Movie retrieveMovieDetails (Context context, long movieId, Client client) {
        Map<String,String> params = movieDetailsURLParameters(context);
        RestAdapter restAdapter = createRestAdapter(client);
        MovieDetailsAPI movieDetailsAPI = restAdapter.create(MovieDetailsAPI.class);
        return movieDetailsAPI.retrieveMovieDetails(movieId, params);
    }
}
