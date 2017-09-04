package com.andredina.ghrepos.helpers;

import com.andredina.ghrepos.data.model.Repository;
import com.andredina.ghrepos.data.remote.GithubApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.mock.BehaviorDelegate;
import rx.Observable;

/**
 * Created by André Dina on 03/09/2017.
 */

public class GithupApiTester implements GithubApi{

    private final BehaviorDelegate<GithubApi> delegate;

    private List<Repository> repositories;

    public GithupApiTester(BehaviorDelegate<GithubApi> delegate, List<Repository> repositories) {
        this.delegate = delegate;
        this.repositories = repositories;
    }


    @Override
    public Observable<List<Repository>> getRepositories(String user, String sort, String direction, int page, int perpage) {
        return Observable.fromCallable(() -> repositories);
    }
}
