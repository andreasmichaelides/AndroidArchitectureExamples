package com.westplay.androidarchitectureexamples.pokemonsearch.domain;

import io.reactivex.Observable;

/**
 * Created by andreas on 15/11/17.
 */

public class ShouldRestoreSearch {

    private final HasSearchResults hasSearchResults;

    public ShouldRestoreSearch(HasSearchResults hasSearchResults) {
        this.hasSearchResults = hasSearchResults;
    }

    public Observable<Boolean> shouldRestore() {
        return hasSearchResults.hasSearchResults()
                .map(hasSearchResults -> !hasSearchResults);
    }
}
