package com.andredina.ghrepos.di.module;

import com.andredina.ghrepos.data.DataManagerContract;
import com.andredina.ghrepos.ui.repolist.RepoListContract;
import com.andredina.ghrepos.ui.repolist.RepoListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by André Dina on 02/09/2017.
 */
@Module
public class ActivityModule {

    @Provides
    RepoListContract.Presenter provideRepositoryPresenter(DataManagerContract dataManager){
        return new RepoListPresenter(dataManager);
    }
}
