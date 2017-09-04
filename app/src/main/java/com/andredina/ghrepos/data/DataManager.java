package com.andredina.ghrepos.data;

import android.util.Log;

import com.andredina.ghrepos.data.local.LocalRepositoryContract;
import com.andredina.ghrepos.data.local.Pref;
import com.andredina.ghrepos.data.model.Repository;
import com.andredina.ghrepos.data.remote.GithubApi;
import com.andredina.ghrepos.data.remote.RemoteRepositoryContract;
import com.andredina.ghrepos.data.remote.SortType;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import io.realm.Realm;
import io.realm.RealmResults;


/**
 * Created by André Dina on 02/09/2017.
 */
public class DataManager implements DataManagerContract {

    private LocalRepositoryContract localRepository;
    private RemoteRepositoryContract remoteRepository;

    public DataManager(LocalRepositoryContract localRepository, RemoteRepositoryContract remoteRepository) {
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
    }

    @Override
    public Observable<List<Repository>> getRepositories(int pageNumber) {
        return Observable.concat(localRepository.getRepositories(pageNumber), remoteRepository.getRepositories(pageNumber))
                .first(repositories -> repositories != null && !repositories.isEmpty());
    }


}
