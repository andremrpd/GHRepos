package com.andredina.ghrepos.ui.repolist;

import android.util.Log;

import com.andredina.ghrepos.R;
import com.andredina.ghrepos.base.BaseView;
import com.andredina.ghrepos.data.DataManagerContract;
import com.andredina.ghrepos.data.model.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by André Dina on 02/09/2017.
 */
public class RepoListPresenter implements RepoListContract.Presenter {

    private static final String TAG = RepoListPresenter.class.getSimpleName();

    private RepoListContract.View view;
    private DataManagerContract dataManager;
    private boolean noMoreItens = false;
    private int pageNumber = 1;

    public RepoListPresenter(DataManagerContract dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void setView(BaseView view) {
        this.view = (RepoListContract.View) view;
    }

    @Override
    public void onLoadMoreRepositories() {

        if (noMoreItens) { return;  }

       dataManager.getRepositories(pageNumber)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(repositories -> {
                    view.addRepositoriesToList(repositories, false);
                    pageNumber++;
                    view.finishLoading();
                }, throwable -> {
                    if (throwable instanceof NoSuchElementException){
                        view.addRepositoriesToList(new ArrayList<>(), true);
                        noMoreItens = true;
                        view.finishLoading();
                    }else{
                        Log.e(TAG, "Error on load data", throwable);
                        view.showError(throwable.getMessage());
                        view.removeProgressbar();
                    }
                });

    }

}
