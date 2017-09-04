package com.andredina.ghrepos.data.local;

import io.realm.Realm;

/**
 * Created by André Dina on 03/09/2017.
 */

public interface RealmCreator {

    Realm getRealm();
    void close();

}
