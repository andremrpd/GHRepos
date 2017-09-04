package com.andredina.ghrepos.ui.listener;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by André Dina on 02/09/2017.
 */

public abstract class EndlessListListener extends RecyclerView.OnScrollListener{

    private int totalItemCount;
    private int lastVisibleItem;
    private int threshold = 3;
    private boolean isLoading = false;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        totalItemCount = linearLayoutManager.getItemCount();
        lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();

        if (!isLoading && totalItemCount <= (lastVisibleItem + threshold)) {
            isLoading = true;
            loadMore();
        }
    }

    public abstract void loadMore();

    public void setLoading(boolean loading) {
        isLoading = loading;
    }
}
