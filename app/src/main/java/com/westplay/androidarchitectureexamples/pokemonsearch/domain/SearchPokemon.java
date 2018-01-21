package com.westplay.androidarchitectureexamples.pokemonsearch.domain;

import com.westplay.androidarchitectureexamples.pokemonsearch.data.repository.PokemonRepository;
import com.westplay.androidarchitectureexamples.pokemonsearch.data.repository.model.PokemonResponse;

import io.reactivex.Observable;

/**
 * Created by andreas on 15/11/17.
 */

public class SearchPokemon {

    private final PokemonRepository pokemonRepository;
    private final SetSearchResultsAsDownloaded setSearchResultsAsDownloaded;

    public SearchPokemon(PokemonRepository pokemonRepository, SetSearchResultsAsDownloaded setSearchResultsAsDownloaded) {
        this.pokemonRepository = pokemonRepository;
        this.setSearchResultsAsDownloaded = setSearchResultsAsDownloaded;
    }

    public Observable<PokemonResponse> search(String pokemonName) {
        return setSearchResultsAsDownloaded.setSearchResultsAsDownloaded()
                .andThen(pokemonRepository.searchPokemon(pokemonName));
    }
}
