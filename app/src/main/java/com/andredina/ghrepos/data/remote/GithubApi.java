package com.andredina.ghrepos.data.remote;

import com.andredina.ghrepos.data.model.Repository;

import java.util.List;

import rx.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * Created by André Dina on 02/09/2017.
 */
public interface GithubApi {

    @GET("users/{user}/repos")
    Observable<List<Repository>> getRepositories(@Path("user") String user,
                                                 @Query("sort") String sort,
                                                 @Query("direction") String direction,
                                                 @Query("page") int page,
                                                 @Query("per_page") int perpage);

}
