package com.westplay.androidarchitectureexamples.core.presentation;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

import java.lang.Override;

public class BaseApplication extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerApplicationComponent.builder()
                .application(this)
                .build();
    }
}
