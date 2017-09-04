package com.andredina.ghrepos.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import com.andredina.ghrepos.GHRepoApp;
import com.andredina.ghrepos.R;
import com.andredina.ghrepos.di.component.ActivityComponent;
import com.andredina.ghrepos.di.component.DaggerActivityComponent;

/**
 * Created by André Dina on 02/09/2017.
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseView{

    private ActivityComponent activityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent = DaggerActivityComponent.builder()
                .appComponent(((GHRepoApp) getApplication()).getAppComponent())
                .build();

    }

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }

    public void showError(String message) {
        Snackbar.make(findViewById(R.id.container), message, Snackbar.LENGTH_LONG).show();
    }
}
