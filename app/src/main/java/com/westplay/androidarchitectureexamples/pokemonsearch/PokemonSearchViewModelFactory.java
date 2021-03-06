package com.westplay.androidarchitectureexamples.pokemonsearch;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.westplay.androidarchitectureexamples.pokemonsearch.domain.SearchPokemon;
import com.westplay.androidarchitectureexamples.pokemonsearch.domain.ShouldRestoreSearch;

class PokemonSearchViewModelFactory implements ViewModelProvider.Factory {

    private final SearchPokemon searchPokemon;
    private final ShouldRestoreSearch shouldRestoreSearch;

    PokemonSearchViewModelFactory(SearchPokemon searchPokemon, ShouldRestoreSearch shouldRestoreSearch) {
        this.searchPokemon = searchPokemon;
        this.shouldRestoreSearch = shouldRestoreSearch;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(PokemonSearchViewModel.class)) {
            return (T) new PokemonSearchViewModel(searchPokemon, shouldRestoreSearch);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
