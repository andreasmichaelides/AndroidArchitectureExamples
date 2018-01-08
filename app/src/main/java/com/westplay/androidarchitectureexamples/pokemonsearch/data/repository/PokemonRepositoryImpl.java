package com.westplay.androidarchitectureexamples.pokemonsearch.data.repository;

import com.westplay.androidarchitectureexamples.core.SchedulersFacade;
import com.westplay.androidarchitectureexamples.pokemonsearch.data.repository.model.PokemonResponse;

import io.reactivex.Observable;

/**
 * Created by andreas on 15/11/17.
 */

public class PokemonRepositoryImpl implements PokemonRepository {

    private final PokemonApi pokemonApi;
    private final SchedulersFacade schedulersFacade;

    public PokemonRepositoryImpl(PokemonApi pokemonApi, SchedulersFacade schedulersFacade) {
        this.pokemonApi = pokemonApi;
        this.schedulersFacade = schedulersFacade;
    }

    @Override
    public Observable<PokemonResponse> searchPokemon(String pokemonName) {
        return pokemonApi.getPokemon(pokemonName)
                .subscribeOn(schedulersFacade.io())
                .observeOn(schedulersFacade.ui());
    }
}
