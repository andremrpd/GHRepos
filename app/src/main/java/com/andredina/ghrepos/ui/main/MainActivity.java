package com.andredina.ghrepos.ui.main;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;

import com.andredina.ghrepos.R;
import com.andredina.ghrepos.base.BaseActivity;
import com.andredina.ghrepos.ui.repolist.RepoListFragment;

public class MainActivity extends BaseActivity implements MainContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupFragment();
    }

    private void setupFragment(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, RepoListFragment.newInstance())
                .commit();
    }

}
