package com.andredina.ghrepos;


import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;

import com.andredina.ghrepos.di.module.AppModule;
import com.andredina.ghrepos.helpers.TestHelper;
import com.andredina.ghrepos.ui.main.MainActivity;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.realm.Realm;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTestFail {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class, true, false);

    private MockWebServer server;

    private Context appContext;

    @Before
    public void setUp() throws Exception {
        appContext = InstrumentationRegistry.getTargetContext();

        server = new MockWebServer();
        server.start();
        AppModule.API_BASE_URL = server.url("/").toString();

        server.enqueue(new MockResponse()
                .setResponseCode(404)
                .setBody(TestHelper.getStringFromFile(appContext, R.raw.repos_notfound)));

        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(TestHelper.getStringFromFile(appContext, R.raw.repos_success)));

    }

   @Test
    public void serviceFail() throws  Exception {
       Realm.init(appContext);
       Realm.getDefaultInstance().executeTransaction(realm -> realm.deleteAll());

       Intent intent = new Intent();
       activityTestRule.launchActivity(intent);

       Thread.sleep(1000);
       onView(withText(R.string.error_on_load)).check(matches(isDisplayed()));

       onView(withText(R.string.try_again)).perform(click());
       Thread.sleep(1000);

       onView(withText("SMSMorse")).check(matches(isDisplayed()));

    }

    @After
    public void tearDown() throws Exception {
        server.shutdown();
    }
}
