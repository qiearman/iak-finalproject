package com.dunianaya.qmovie.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Qi on 8/29/2017.
 */

public class ApiService {

    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w125/";
    public static final String BASE_IMAGE_W500_URL = "https://image.tmdb.org/t/p/w500/";
    public static final String API_KEY = "f72f27dc4e8e194521c111cb5b4a6f96";

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    private static HttpLoggingInterceptor logging =
            new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY);

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public static <S> S createService(Class<S> serviceClass) {

        if (!httpClient.interceptors().contains(logging)) {
            httpClient.addInterceptor(logging);
            builder.client(httpClient.build());
            retrofit = builder.build();
        }

        return retrofit.create(serviceClass);
    }

    public static IMovieDB getMovieService() {
        return createService(IMovieDB.class);
    }

    public interface IMovieDB {

        // Popular
        @GET("movie/popular")
        Call<String> popular(
                @Query("api_key") String apiKey,
                @Query("page") int page
        );

        // Top Rated
        @GET("movie/top_rated")
        Call<String> topRated(
                @Query("api_key") String apiKey,
                @Query("page") int page
        );

        // Upcoming
        @GET("movie/upcoming")
        Call<String> upcoming(
                @Query("api_key") String apiKey,
                @Query("page") int page
        );
    }
}


