package com.andredina.ghrepos.di.component;

import com.andredina.ghrepos.di.annotations.PerActivity;
import com.andredina.ghrepos.di.module.ActivityModule;
import com.andredina.ghrepos.ui.main.MainActivity;
import com.andredina.ghrepos.ui.repolist.RepoListFragment;

import dagger.Component;

/**
 * Created by André Dina on 02/09/2017.
 */

@PerActivity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity activity);
    void inject(RepoListFragment fragment);

}