package com.andredina.ghrepos.data.local;

import android.content.SharedPreferences;

/**
 * Created by André Dina on 03/09/2017.
 */

public class Pref implements PrefContract{

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public Pref(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        this.editor = sharedPreferences.edit();
    }

    private class Fields {
        static final String LAST_PAGE_LOADED = "lastPageLoaded";
        static final String REPO_OWNER = "repoOwner";
        static final String PAGE_SIZE = "pageSize";
    }

    private class Defaults {
        static final String DEFAULT_REPO_OWNER = "JakeWharton";
        static final int DEFAULT_PAGE_SIZE = 15;
    }

    @Override
    public String getRepoOwner() {
        return sharedPreferences.getString(Fields.REPO_OWNER, Defaults.DEFAULT_REPO_OWNER);
    }

    @Override
    public void setRepoOwner(String repoOwner) {
        editor.putString(Fields.REPO_OWNER, repoOwner).apply();
    }

    @Override
    public int getPageSize() {
        return sharedPreferences.getInt(Fields.PAGE_SIZE, Defaults.DEFAULT_PAGE_SIZE);
    }

    @Override
    public void setPageSize(int pageSize) {
        editor.putInt(Fields.PAGE_SIZE, pageSize).apply();
    }

    @Override
    public int getLastPageLoaded() {
        return sharedPreferences.getInt(Fields.LAST_PAGE_LOADED, 0);
    }

    @Override
    public void setLastPageLoaded(int lastPageLoaded) {
        editor.putInt(Fields.LAST_PAGE_LOADED, lastPageLoaded).apply();
    }


}
