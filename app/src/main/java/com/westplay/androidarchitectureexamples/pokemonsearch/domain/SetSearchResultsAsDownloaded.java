package com.westplay.androidarchitectureexamples.pokemonsearch.domain;

import io.reactivex.Completable;
import io.reactivex.subjects.Subject;

public class SetSearchResultsAsDownloaded {

    private final Subject<Boolean> resultsPresent;

    public SetSearchResultsAsDownloaded(Subject<Boolean> resultsPresent) {
        this.resultsPresent = resultsPresent;
    }

    Completable setSearchResultsAsDownloaded() {
        return Completable.fromAction(() -> resultsPresent.onNext(true));
    }
}
