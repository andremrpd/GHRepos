package com.andredina.ghrepos.ui.main;

import com.andredina.ghrepos.base.BaseView;

/**
 * Created by André Dina on 03/09/2017.
 */

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;

    @Override
    public void setView(BaseView view) {
        this.view = (MainContract.View) view;
    }
}
