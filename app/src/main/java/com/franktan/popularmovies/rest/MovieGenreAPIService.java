package com.franktan.popularmovies.rest;

import android.content.Context;

import com.franktan.popularmovies.model.Genre;
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
import retrofit.converter.GsonConverter;
import retrofit.http.GET;
import retrofit.http.QueryMap;

/**
 * Created by tan on 20/09/2015.
 * REST API call to retrieve a list of Genres
 */
public class MovieGenreAPIService {
    /**
     * The interface Retrofit takes to create REST api call
     */
    public interface MovieGenresAPI {
        @GET("/3/genre/movie/list")
        List<Genre> retrieveGenres(@QueryMap Map<String, String> options);
    }

    private static class GenresDeserializer implements JsonDeserializer<List<Genre>> {
        @Override
        public List<Genre> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            JsonArray genres = jsonObject.getAsJsonArray("genres");
            Type genresListType = new TypeToken<List<Genre>>() {}.getType();
            return new Gson().fromJson(genres, genresListType);

        }
    }

    /**
     * Create a custom gson with our custom deserializer
     * @return
     */
    private static Gson genresCustomGson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(List.class, new GenresDeserializer())
                .create();
    }

    public static List<Genre> retrieveMovieGenres (Context context) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Constants.MOVIEDB_BASE_URL)
                .setConverter(new GsonConverter(genresCustomGson()))
                .build();
        MovieGenresAPI movieGenresAPI = restAdapter.create(MovieGenresAPI.class);
        Map<String, String> parameters = new HashMap<>(1);
        parameters.put("api_key", Utilities.getMovieDBApiKey(context));
        return movieGenresAPI.retrieveGenres(parameters);
    }
}
