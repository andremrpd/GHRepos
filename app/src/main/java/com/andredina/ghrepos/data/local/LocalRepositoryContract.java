package com.andredina.ghrepos.data.local;

import com.andredina.ghrepos.data.AppRepositoryContract;
import com.andredina.ghrepos.data.model.Repository;

import java.util.List;

/**
 * Created by André Dina on 03/09/2017.
 */

public interface LocalRepositoryContract extends AppRepositoryContract {

    void save(List<Repository> repositories);

}
