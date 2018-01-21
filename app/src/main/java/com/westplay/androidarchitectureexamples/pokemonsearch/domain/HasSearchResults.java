package com.westplay.androidarchitectureexamples.pokemonsearch.domain;

import io.reactivex.Observable;
import io.reactivex.subjects.Subject;

/**
 * Created by andreas on 15/11/17.
 */

public class HasSearchResults {

    private final Subject<Boolean> resultsPresent;

    public HasSearchResults(Subject<Boolean> resultsPresent) {
        this.resultsPresent = resultsPresent;
    }

    Observable<Boolean> hasSearchResults() {
        return resultsPresent;
    }

}
