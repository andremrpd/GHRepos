package com.andredina.ghrepos.di.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.andredina.ghrepos.BuildConfig;
import com.andredina.ghrepos.data.DataManager;
import com.andredina.ghrepos.data.DataManagerContract;
import com.andredina.ghrepos.data.local.LocalRepository;
import com.andredina.ghrepos.data.local.LocalRepositoryContract;
import com.andredina.ghrepos.data.local.Pref;
import com.andredina.ghrepos.data.local.PrefContract;
import com.andredina.ghrepos.data.local.RealmCreator;
import com.andredina.ghrepos.data.local.RealmCreatorDefault;
import com.andredina.ghrepos.data.remote.GithubApi;
import com.andredina.ghrepos.data.remote.RemoteRepository;
import com.andredina.ghrepos.data.remote.RemoteRepositoryContract;
import com.andredina.ghrepos.di.annotations.ApplicationContext;
import com.andredina.ghrepos.utils.DateUtils;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;


/**
 * Created by André Dina on 02/09/2017.
 */
@Module
public class AppModule {

    public static String API_BASE_URL = "https://api.github.com/";
    private final Application application;

    public AppModule(Application application){
        this.application = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext(){
        return application;
    }

    @Provides
    Application provideApplication(){
        return application;
    }

    @Provides
    DataManagerContract provideDataManager(LocalRepositoryContract localRepository, RemoteRepositoryContract remoteRepository){
        return new DataManager(localRepository, remoteRepository);
    }

    @Provides
    RemoteRepositoryContract provideRemoteRepository(GithubApi githubApi, PrefContract pref, LocalRepositoryContract localRepository){
        return new RemoteRepository(githubApi, pref, localRepository);
    }

    @Provides
    LocalRepositoryContract provideLocalRepository(PrefContract pref, RealmCreator realmCreator){
        return new LocalRepository(pref, realmCreator);
    }

    @Provides
    @Singleton
    PrefContract providePref(SharedPreferences preferences){
        return new Pref(preferences);
    }

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    RealmCreator provideRealmCreator(){
        return  new RealmCreatorDefault();
    }

    @Singleton
    @Provides
    GithubApi provideGithubApi(Retrofit retrofit){
        return retrofit.create(GithubApi.class);
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(Converter.Factory factory, OkHttpClient client){
        return new Retrofit.Builder()
                .client(client)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(API_BASE_URL)
                .build();
    }

    @Singleton
    @Provides
    Converter.Factory provideConverterFactory(ObjectMapper mapper){
        return JacksonConverterFactory.create(mapper);
    }

    @Singleton
    @Provides
    ObjectMapper provideMapper(){
        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .setDateFormat(DateUtils.getDefaultDateFormat())
                .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

        return mapper;
    }

    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient(){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);
            httpClient.addInterceptor(logging);
        }
        return httpClient.build();
    }

}
