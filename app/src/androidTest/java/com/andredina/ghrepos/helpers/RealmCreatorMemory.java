package com.andredina.ghrepos.helpers;

import com.andredina.ghrepos.data.local.RealmCreator;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by André Dina on 03/09/2017.
 */

public class RealmCreatorMemory implements RealmCreator {

    @Override
    public Realm getRealm() {
        RealmConfiguration config = new RealmConfiguration.Builder().inMemory().name("testedb").build();
        return Realm.getInstance(config);
    }

    @Override
    public void close() {
        getRealm().close();
    }

}
