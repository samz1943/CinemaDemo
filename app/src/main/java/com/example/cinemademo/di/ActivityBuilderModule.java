package com.example.cinemademo.di;

import com.example.cinemademo.di.main.MainFragmentBuilderModule;
import com.example.cinemademo.di.main.MainModule;
import com.example.cinemademo.di.main.MainViewModelsModule;
import com.example.cinemademo.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(
            modules = {MainFragmentBuilderModule.class, MainViewModelsModule.class, MainModule.class}
    )
    abstract MainActivity contributeMainActivity();
}
