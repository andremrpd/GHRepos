package com.andredina.ghrepos;

import android.app.Application;

import com.andredina.ghrepos.di.component.AppComponent;
import com.andredina.ghrepos.di.component.DaggerAppComponent;
import com.andredina.ghrepos.di.module.AppModule;

import io.realm.Realm;

/**
 * Created by André Dina on 02/09/2017.
 */

public class GHRepoApp extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
