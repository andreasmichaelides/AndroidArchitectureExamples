package com.westplay.androidarchitectureexamples.pokemonsearch;

import com.westplay.androidarchitectureexamples.core.SchedulersFacade;
import com.westplay.androidarchitectureexamples.core.presentation.ActivityScope;
import com.westplay.androidarchitectureexamples.pokemonsearch.data.repository.PokemonApi;
import com.westplay.androidarchitectureexamples.pokemonsearch.data.repository.PokemonModule;
import com.westplay.androidarchitectureexamples.pokemonsearch.data.repository.PokemonRepository;
import com.westplay.androidarchitectureexamples.pokemonsearch.data.repository.PokemonRepositoryImpl;
import com.westplay.androidarchitectureexamples.pokemonsearch.domain.HasSearchResults;
import com.westplay.androidarchitectureexamples.pokemonsearch.domain.SearchPokemon;
import com.westplay.androidarchitectureexamples.pokemonsearch.domain.ShouldRestoreSearch;

import dagger.Module;
import dagger.Provides;

@Module(includes = PokemonModule.class)
public abstract class PokemonSearchModule {

    @Provides
    @ActivityScope
    static PokemonSearchViewModelFactory providePokemonSearch(SearchPokemon searchPokemon, ShouldRestoreSearch shouldRestoreSearch) {
        return new PokemonSearchViewModelFactory(searchPokemon, shouldRestoreSearch);
    }

    @Provides
    @ActivityScope
    static SchedulersFacade provideSchedulersFacade() {
        return new SchedulersFacade();
    }

    @Provides
    @ActivityScope
    static PokemonRepository providePokemonRepository(PokemonApi pokemonApi, SchedulersFacade schedulersFacade) {
        return new PokemonRepositoryImpl(pokemonApi, schedulersFacade);
    }

    @Provides
    @ActivityScope
    static SearchPokemon provideSearchPokemon(PokemonRepository pokemonRepository, HasSearchResults hasSearchResults) {
        return new SearchPokemon(pokemonRepository, hasSearchResults);
    }

    @Provides
    @ActivityScope
    static ShouldRestoreSearch provideRestoreSearch(HasSearchResults hasSearchResults) {
        return new ShouldRestoreSearch(hasSearchResults);
    }

    @Provides
    @ActivityScope
    static HasSearchResults provideSearchStateManager() {
        return new HasSearchResults();
    }
}
