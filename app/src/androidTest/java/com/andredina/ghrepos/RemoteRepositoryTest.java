package com.andredina.ghrepos;

import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;

import com.andredina.ghrepos.data.model.Repository;
import com.andredina.ghrepos.data.remote.GithubApi;
import com.andredina.ghrepos.helpers.GithupApiTester;
import com.andredina.ghrepos.helpers.TestHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;
import rx.Observable;
import rx.observers.TestSubscriber;

/**
 * Created by André Dina on 03/09/2017.
 */
@RunWith(AndroidJUnit4.class)
public class RemoteRepositoryTest {

    private MockRetrofit mockRetrofit;
    private Retrofit retrofit;

    @Before
    public void setUp() throws Exception {
        retrofit = new Retrofit.Builder().baseUrl("http://test.com")
                .client(new OkHttpClient())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        NetworkBehavior behavior = NetworkBehavior.create();

        mockRetrofit = new MockRetrofit.Builder(retrofit)
                .networkBehavior(behavior)
                .build();
    }

    @Test
    public void testGetRepositories()throws Exception{
        BehaviorDelegate<GithubApi> delegate = mockRetrofit.create(GithubApi.class);
        List<Repository> repositories = TestHelper.getDummyRepositories();
        GithubApi githubApi = new GithupApiTester(delegate, repositories);

        Observable<List<Repository>> observable = githubApi.getRepositories("user", "created", "asc", 1, 15);
        TestSubscriber<List<Repository>> testSubscriber = new TestSubscriber<>();
        observable.subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();
        testSubscriber.assertValueCount(1);
        testSubscriber.assertValue(repositories);

    }
}
