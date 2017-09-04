package com.andredina.ghrepos.ui.repolist;

import com.andredina.ghrepos.base.BasePresenter;
import com.andredina.ghrepos.base.BaseView;
import com.andredina.ghrepos.data.model.Repository;

import java.util.List;

/**
 * Created by André Dina on 03/09/2017.
 */

public class RepoListContract {

    public interface Presenter extends BasePresenter {

        void onLoadMoreRepositories();

    }

    public interface View extends BaseView{

        void addRepositoriesToList(List<Repository> repositories, boolean last);

        void setupList();

        void finishLoading();

        void removeProgressbar();
    }

}
