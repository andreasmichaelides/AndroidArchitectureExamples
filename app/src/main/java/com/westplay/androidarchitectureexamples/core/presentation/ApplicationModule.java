package com.westplay.androidarchitectureexamples.core.presentation;

import dagger.Module;
import dagger.android.AndroidInjectionModule;

@Module(
        includes = AndroidInjectionModule.class
)
abstract class ApplicationModule {

    @ActivityScope
    @dagger.android.ContributesAndroidInjector(
            modules = com.westplay.androidarchitectureexamples.pokemonsearch.PokemonSearchModule.class
    )
    public abstract com.westplay.androidarchitectureexamples.pokemonsearch.PokemonSearchActivity pokemonSearchActivityInjector();
}
