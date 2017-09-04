package com.andredina.ghrepos.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.andredina.ghrepos.R;
import com.andredina.ghrepos.data.model.Repository;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by André Dina on 02/09/2017.
 */

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.ViewHolder> {

    private static final int VIEW_TYPE_ITEM = 0;
    private static final int VIEW_TYPE_LOADING = 1;

    private List<Repository> repositories;

    public RepoAdapter(List<Repository> repositories){
        this.repositories = repositories;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_item, parent, false);
            return new RepoHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.loading_item, parent, false);
            return new LoadingHolder(view);
        }

        return null;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(repositories.get(position));
    }

    @Override
    public int getItemCount() {
        return repositories == null ? 0 : repositories.size();
    }

    @Override
    public int getItemViewType(int position) {
        return repositories.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    class RepoHolder extends ViewHolder{

        @BindView(R.id.txtRepoName)
        TextView txtRepoName;

        @BindView(R.id.txtDescription)
        TextView txtDescription;

        @BindView(R.id.txtLanguage)
        TextView txtLanguage;

        RepoHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Repository repo){
            txtRepoName.setText(repo.getName());
            txtDescription.setText(repo.getDescription());
            txtLanguage.setText(repo.getLanguage());
        }
    }

    class LoadingHolder extends ViewHolder {

        @BindView(R.id.progressBar)
        ProgressBar progressBar;

        LoadingHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Repository repo){
            progressBar.setIndeterminate(true);
        }
    }

    abstract class ViewHolder extends RecyclerView.ViewHolder{

        ViewHolder(View itemView) {
            super(itemView);
        }

        abstract void bind(Repository repo);
    }
}
