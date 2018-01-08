package com.westplay.androidarchitectureexamples.pokemonsearch.domain;

import com.westplay.androidarchitectureexamples.pokemonsearch.data.repository.PokemonRepository;
import com.westplay.androidarchitectureexamples.pokemonsearch.data.repository.model.PokemonResponse;

import io.reactivex.Observable;

/**
 * Created by andreas on 15/11/17.
 */

public class SearchPokemon {

    private final PokemonRepository pokemonRepository;
    private final HasSearchResults hasSearchResults;

    public SearchPokemon(PokemonRepository pokemonRepository, HasSearchResults hasSearchResults) {
        this.pokemonRepository = pokemonRepository;
        this.hasSearchResults = hasSearchResults;
    }

    public Observable<PokemonResponse> search(String pokemonName) {
        return hasSearchResults.setSearchResultsAsDownloaded()
                .andThen(pokemonRepository.searchPokemon(pokemonName));
    }
}
