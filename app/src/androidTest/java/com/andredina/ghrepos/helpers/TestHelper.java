package com.andredina.ghrepos.helpers;

import android.content.Context;

import com.andredina.ghrepos.R;
import com.andredina.ghrepos.data.model.Owner;
import com.andredina.ghrepos.data.model.Repository;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by André Dina on 03/09/2017.
 */

public class TestHelper {

    public static Repository createRepository(long id){

        Repository repo = new Repository();
        repo.setId(id);
        repo.setName("Repository " + id);
        repo.setDescription("My Very Beatiful Repository");
        repo.setLanguage("Java");
        repo.setSize(1000l);

        Owner owner = new Owner();
        owner.setId(id);
        owner.setLogin("Owner " + id);

        repo.setOwner(owner);

        return repo;
    }

    public static List<Repository> getDummyRepositories(){
        List<Repository> repositories = new ArrayList<>();
        for (int i = 1; i <= 3; i++){
            repositories.add(createRepository(1));
        }
        return repositories;
    }

    public static String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }

    public static String getStringFromFile(Context context, int resource) throws Exception {
        final InputStream stream = context.getResources().openRawResource(resource);
        String ret = convertStreamToString(stream);
        stream.close();
        return ret;
    }
}
