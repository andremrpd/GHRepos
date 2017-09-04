package com.andredina.ghrepos.di.component;

import android.content.Context;

import com.andredina.ghrepos.data.DataManagerContract;
import com.andredina.ghrepos.di.annotations.ApplicationContext;
import com.andredina.ghrepos.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Provides;

/**
 * Created by André Dina on 02/09/2017.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    @ApplicationContext
    Context context();

    DataManagerContract getDataManager();

}
