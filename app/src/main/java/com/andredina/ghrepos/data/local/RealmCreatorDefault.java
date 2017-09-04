package com.andredina.ghrepos.data.local;

import io.realm.Realm;

/**
 * Created by André Dina on 03/09/2017.
 */

public class RealmCreatorDefault implements RealmCreator {
    @Override
    public Realm getRealm() {
        return Realm.getDefaultInstance();
    }

    @Override
    public void close() {
        Realm.getDefaultInstance().close();
    }
}
