package com.andredina.ghrepos.data;

import com.andredina.ghrepos.data.model.Repository;

import java.util.List;

import rx.Observable;


/**
 * Created by André Dina on 02/09/2017.
 */
public interface DataManagerContract {

    Observable<List<Repository>> getRepositories(int pageNumber);

}
