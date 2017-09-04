package com.andredina.ghrepos.data.model;

import com.google.common.base.Objects;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by André Dina on 02/09/2017.
 */
public class Owner extends RealmObject {

    @PrimaryKey
    private Long id;
    private String login;
    private String avatarUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owner owner = (Owner) o;
        return Objects.equal(id, owner.id) &&
                Objects.equal(login, owner.login);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, login);
    }
}
