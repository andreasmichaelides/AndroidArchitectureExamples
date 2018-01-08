package com.westplay.androidarchitectureexamples.pokemonsearch.data.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by andreas on 14/11/17.
 */

class PokemonRetrofit {

    private static final String BASE_URL = "https://pokeapi.co";
    private final Retrofit retrofit;

    PokemonRetrofit() {
        this.retrofit = createRetrofitInstance();
    }

    private Retrofit createRetrofitInstance() {
        final Gson gson = new GsonBuilder().
                registerTypeAdapterFactory(AdapterFactory.create())
                .create();

        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .build();
    }

    <T> T createService(Class<T> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
