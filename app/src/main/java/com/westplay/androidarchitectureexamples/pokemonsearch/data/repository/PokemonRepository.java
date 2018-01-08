package com.westplay.androidarchitectureexamples.pokemonsearch.data.repository;

import com.westplay.androidarchitectureexamples.pokemonsearch.data.repository.model.PokemonResponse;

import io.reactivex.Observable;

/**
 * Created by andreas on 15/11/17.
 */

public interface PokemonRepository {

    Observable<PokemonResponse> searchPokemon(String pokemonName);

}
