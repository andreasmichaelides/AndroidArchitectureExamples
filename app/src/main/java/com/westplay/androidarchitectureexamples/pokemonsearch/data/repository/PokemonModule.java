package com.westplay.androidarchitectureexamples.pokemonsearch.data.repository;

import com.westplay.androidarchitectureexamples.core.presentation.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by andreas on 15/11/17.
 */

@Module
public class PokemonModule {

    @Provides
    @ActivityScope
    static PokemonRetrofit providePokemonService() {
        return new PokemonRetrofit();
    }

    @Provides
    @ActivityScope
    static PokemonApi providePokemonApi(PokemonRetrofit pokemonRetrofit) {
        return pokemonRetrofit.createService(PokemonApi.class);
    }
}
