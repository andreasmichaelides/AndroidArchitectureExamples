package com.westplay.androidarchitectureexamples.core.presentation;

public interface Component<T> {
    void inject(T objectToBeInjectedWithModules);
}
