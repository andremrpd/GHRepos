package com.andredina.ghrepos.ui.repolist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andredina.ghrepos.R;
import com.andredina.ghrepos.base.BaseFragment;
import com.andredina.ghrepos.data.model.Repository;
import com.andredina.ghrepos.di.component.ActivityComponent;
import com.andredina.ghrepos.ui.adapter.RepoAdapter;
import com.andredina.ghrepos.ui.listener.EndlessListListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by André Dina on 02/09/2017.
 */
public class RepoListFragment extends BaseFragment implements RepoListContract.View {

    @Inject
    RepoListContract.Presenter presenter;

    @BindView(R.id.repositoryList)
    RecyclerView repositoryList;

    private List<Repository> repositories = new ArrayList<>();

    private EndlessListListener listListener;

    public static RepoListFragment newInstance() {
        return new RepoListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_repo_list, container, false);

        ButterKnife.bind(this, view);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            presenter.setView(this);
        }

        setupList();

        return view;
    }

    @Override
    public void addRepositoriesToList(List<Repository> repositories, boolean last) {
        if (this.repositories.size() > 0) {
            this.repositories.remove(this.repositories.size() - 1);
        }
        this.repositories.addAll(repositories);
        if(!last){
            this.repositories.add(null);
        }
        repositoryList.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void setupList() {
        repositories.add(null);
        repositoryList.setAdapter(new RepoAdapter(repositories));
        repositoryList.setLayoutManager(new LinearLayoutManager(getActivity()));
        listListener = new EndlessListListener() {
            @Override
            public void loadMore() {
                presenter.onLoadMoreRepositories();
            }
        };
        repositoryList.addOnScrollListener(listListener);
    }

    @Override
    public void showError(String message) {
        Snackbar snackbar = Snackbar.make(getActivity().findViewById(R.id.container), getString(R.string.error_on_load), Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.try_again, v ->{
                    repositories.add(null);
                    repositoryList.getAdapter().notifyItemInserted(repositories.size()-1);
                    presenter.onLoadMoreRepositories();
                } );

        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));

        snackbar.show();
    }

    @Override
    public void finishLoading() {
        listListener.setLoading(false);
    }

    @Override
    public void removeProgressbar(){
        if (!repositories.isEmpty() && repositories.get(repositories.size()-1) == null){
            repositories.remove(repositories.size() - 1);
            repositoryList.getAdapter().notifyItemRemoved(repositories.size());
        }
    }
}
