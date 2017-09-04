package com.andredina.ghrepos.data.local;

/**
 * Created by André Dina on 03/09/2017.
 */

public interface PrefContract {

    String getRepoOwner();
    void setRepoOwner(String repoOwner);

    int getPageSize();
    void setPageSize(int pageSize);

    int getLastPageLoaded();
    void setLastPageLoaded(int lastPageLoaded);
}
