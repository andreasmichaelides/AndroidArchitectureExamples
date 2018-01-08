package com.westplay.androidarchitectureexamples.pokemonsearch;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.westplay.androidarchitectureexamples.core.presentation.SingleLiveEvent;
import com.westplay.androidarchitectureexamples.pokemonsearch.data.repository.model.PokemonResponse;
import com.westplay.androidarchitectureexamples.pokemonsearch.domain.SearchPokemon;
import com.westplay.androidarchitectureexamples.pokemonsearch.domain.ShouldRestoreSearch;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

class PokemonSearchViewModel extends ViewModel {

    private final MutableLiveData<PokemonResponse> pokemonSearchResult = new MutableLiveData<>();
    private final SingleLiveEvent<String> pokemonSearchError = new SingleLiveEvent<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private final Subject<String> searchForPokemon = PublishSubject.create();
    private final Subject<String> restoreSearch = PublishSubject.create();
    private CompositeDisposable subscriptions = new CompositeDisposable();

    PokemonSearchViewModel(SearchPokemon searchPokemon, ShouldRestoreSearch shouldRestoreSearch) {
        subscriptions.addAll(
                searchForPokemon.switchMap(pokemonQuery -> searchPokemon.search(pokemonQuery)
                        .doOnSubscribe(disposable -> loading.setValue(true))
                        .doOnError(throwable -> pokemonSearchError.setValue(throwable.getMessage()))
                        .doOnError(throwable -> loading.setValue(false))
                        .onErrorResumeNext(Observable.empty()))
                        .subscribe(pokemonResponse -> {
                            pokemonSearchResult.setValue(pokemonResponse);
                            loading.setValue(false);
                        }),
                restoreSearch.flatMap(query -> shouldRestoreSearch.shouldRestore()
                        .filter(restoreSearch -> restoreSearch)
                        .map(ignored -> query))
                        .subscribe(query -> searchPokemon(query))
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        subscriptions.clear();
    }

    MutableLiveData<PokemonResponse> searchResult() {
        return pokemonSearchResult;
    }

    SingleLiveEvent<String> searchError() {
        return pokemonSearchError;
    }

    MutableLiveData<Boolean> isLoading() {
        return loading;
    }

    void searchPokemon(String pokemonName) {
        searchForPokemon.onNext(pokemonName);
    }

    void restoreSearch(String query) {
        restoreSearch.onNext(query);
    }
}
