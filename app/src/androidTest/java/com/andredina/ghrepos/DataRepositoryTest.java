package com.andredina.ghrepos;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.andredina.ghrepos.data.local.LocalRepository;
import com.andredina.ghrepos.data.local.LocalRepositoryContract;
import com.andredina.ghrepos.data.local.PrefContract;
import com.andredina.ghrepos.data.local.RealmCreator;
import com.andredina.ghrepos.data.model.Repository;
import com.andredina.ghrepos.helpers.RealmCreatorMemory;
import com.andredina.ghrepos.helpers.TestHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.realm.Realm;
import rx.observers.TestSubscriber;

import static org.junit.Assert.*;
/**
 * Created by André Dina on 03/09/2017.
 */
@RunWith(AndroidJUnit4.class)
public class DataRepositoryTest {

    private LocalRepositoryContract localRepository;

    private RealmCreator realm;

    @Mock
    PrefContract pref;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        Context appContext = InstrumentationRegistry.getTargetContext();
        Realm.init(appContext);

        realm = new RealmCreatorMemory();
        localRepository = new LocalRepository(pref, realm);

        when(pref.getPageSize()).thenReturn(15);
        when(pref.getLastPageLoaded()).thenReturn(0);

    }

    @After
    public void tearDown() throws Exception {
        realm.getRealm().close();
    }

    @Test
    public void testSaveRepositories() throws Exception{
        List<Repository> repositories = TestHelper.getDummyRepositories();
        localRepository.save(repositories);

        TestSubscriber<List<Repository>> testSubscriber = new TestSubscriber<>();

        List<Repository> savedRepositories = localRepository.getRepositories(1).toBlocking().first();
        assertEquals(repositories, savedRepositories);

        localRepository.getRepositories(1).subscribe(testSubscriber);
        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();
        testSubscriber.assertValueCount(1);
        testSubscriber.assertValue(repositories);
    }


}
