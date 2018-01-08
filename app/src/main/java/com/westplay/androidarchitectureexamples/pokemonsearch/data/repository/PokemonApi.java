package com.westplay.androidarchitectureexamples.pokemonsearch.data.repository;

import android.support.annotation.NonNull;

import com.westplay.androidarchitectureexamples.pokemonsearch.data.repository.model.PokemonResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by andreas on 14/11/17.
 */

public interface PokemonApi {

    @Headers({"Content-Type: application/json"})
    @GET("/api/v2/pokemon/{pokemonName}/")
    Observable<PokemonResponse> getPokemon(@NonNull @Path("pokemonName") String pokemonName);
}
