package com.khalidsyfullah.boimela.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static String BASE_URL = "https://api.boimelafoundation.com";
    private static Retrofit retrofit = null;

    public static RestAPI createRetrofitClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build();

        }
        return retrofit.create(RestAPI.class);

    }
}
