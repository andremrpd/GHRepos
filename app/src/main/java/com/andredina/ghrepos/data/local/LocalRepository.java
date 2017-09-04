package com.andredina.ghrepos.data.local;

import android.content.SharedPreferences;

import com.andredina.ghrepos.data.DataManagerContract;
import com.andredina.ghrepos.data.model.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.SocketHandler;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;

/**
 * Created by André Dina on 03/09/2017.
 */
public class LocalRepository implements LocalRepositoryContract {

    private PrefContract pref;
    private RealmCreator realmCreator;

    public LocalRepository(PrefContract pref, RealmCreator realmCreator) {
        this.pref = pref;
        this.realmCreator = realmCreator;
    }

    @Override
    public Observable<List<Repository>> getRepositories(int pageNumber) {
        return Observable.fromCallable(() -> {
            Realm realm = realmCreator.getRealm();
            RealmResults<Repository> results = realm.where(Repository.class).findAll().sort("createdAt");

            int pageSize = pref.getPageSize();
            int fromIndex = pageSize * (pageNumber-1);
            int toIndex = fromIndex + pageSize - 1;

            List<Repository> repositories = new ArrayList<>();

            if (results.size() > toIndex){
                repositories = realm.copyFromRealm(results.subList(fromIndex, toIndex));
            }else if(results.size() > fromIndex){
                repositories = realm.copyFromRealm(results.subList(fromIndex, results.size()));
            }

            realmCreator.close();

            return repositories;
        });
    }

    @Override
    public void save(List<Repository> repositories){
        Realm realm = null;

        try{
            realm = realmCreator.getRealm();
            realm.executeTransaction(
                    realm1 -> realm1.copyToRealmOrUpdate(repositories)
            );
        }finally {
            if(realm != null) {
                realmCreator.close();
            }
        }
    }
}
