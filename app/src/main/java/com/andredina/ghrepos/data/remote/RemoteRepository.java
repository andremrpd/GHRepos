package com.andredina.ghrepos.data.remote;

import com.andredina.ghrepos.data.DataManagerContract;
import com.andredina.ghrepos.data.local.LocalRepository;
import com.andredina.ghrepos.data.local.LocalRepositoryContract;
import com.andredina.ghrepos.data.local.Pref;
import com.andredina.ghrepos.data.local.PrefContract;
import com.andredina.ghrepos.data.model.Repository;

import java.util.List;

import io.realm.Realm;
import rx.Observable;

/**
 * Created by André Dina on 03/09/2017.
 */

public class RemoteRepository implements RemoteRepositoryContract {

    private GithubApi githubApi;
    private PrefContract pref;
    private LocalRepositoryContract localRepository;

    public RemoteRepository(GithubApi githubApi, PrefContract pref, LocalRepositoryContract localRepository) {
        this.githubApi = githubApi;
        this.pref = pref;
        this.localRepository = localRepository;
    }

    @Override
    public Observable<List<Repository>> getRepositories(int pageNumber) {
        return githubApi.getRepositories(pref.getRepoOwner(), SortType.SORT_CREATED, SortType.DIRECTION_ASC, pageNumber, pref.getPageSize())
                .doOnNext(
                        repositories -> localRepository.save(repositories)
                );
    }
}
