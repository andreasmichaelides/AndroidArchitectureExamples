package com.westplay.androidarchitectureexamples.pokemonsearch;

import com.westplay.androidarchitectureexamples.core.SchedulersFacade;
import com.westplay.androidarchitectureexamples.core.presentation.ActivityScope;
import com.westplay.androidarchitectureexamples.pokemonsearch.data.repository.PokemonApi;
import com.westplay.androidarchitectureexamples.pokemonsearch.data.repository.PokemonModule;
import com.westplay.androidarchitectureexamples.pokemonsearch.data.repository.PokemonRepository;
import com.westplay.androidarchitectureexamples.pokemonsearch.data.repository.PokemonRepositoryImpl;
import com.westplay.androidarchitectureexamples.pokemonsearch.domain.HasSearchResults;
import com.westplay.androidarchitectureexamples.pokemonsearch.domain.SearchPokemon;
import com.westplay.androidarchitectureexamples.pokemonsearch.domain.SetSearchResultsAsDownloaded;
import com.westplay.androidarchitectureexamples.pokemonsearch.domain.ShouldRestoreSearch;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

@Module(includes = PokemonModule.class)
public abstract class PokemonSearchModule {

    private static final String SUBJECT_SEARCH_RESULTS_DOWNLOADED = "SUBJECT_SEARCH_RESULTS_DOWNLOADED";

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

    @Named(SUBJECT_SEARCH_RESULTS_DOWNLOADED)
    @Provides
    @ActivityScope
    static Subject<Boolean> provideSearchResultsAsDownloaded() {
        return BehaviorSubject.createDefault(false);
    }

    @Provides
    @ActivityScope
    static SetSearchResultsAsDownloaded provideSetSearchResultsAsDownloaded(@Named(SUBJECT_SEARCH_RESULTS_DOWNLOADED)
                                                                                    Subject<Boolean> resultsPresent) {
        return new SetSearchResultsAsDownloaded(resultsPresent);
    }

    @Provides
    @ActivityScope
    static SearchPokemon provideSearchPokemon(PokemonRepository pokemonRepository, SetSearchResultsAsDownloaded setSearchResultsAsDownloaded) {
        return new SearchPokemon(pokemonRepository, setSearchResultsAsDownloaded);
    }

    @Provides
    @ActivityScope
    static ShouldRestoreSearch provideRestoreSearch(HasSearchResults hasSearchResults) {
        return new ShouldRestoreSearch(hasSearchResults);
    }

    @Provides
    @ActivityScope
    static HasSearchResults provideHasSearchResults(@Named(SUBJECT_SEARCH_RESULTS_DOWNLOADED)
                                                              Subject<Boolean> resultsPresent) {
        return new HasSearchResults(resultsPresent);
    }
}
