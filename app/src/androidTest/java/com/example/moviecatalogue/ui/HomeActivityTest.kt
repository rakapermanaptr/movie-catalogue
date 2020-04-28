package com.example.moviecatalogue.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.example.moviecatalogue.FakeData
import com.example.moviecatalogue.R
import com.example.moviecatalogue.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before

import org.junit.Rule
import org.junit.Test

class HomeActivityTest {

    private val dummyMovies = FakeData.getDummyRemoteMovies()
    private val dummyTvShows = FakeData.getDummyRemoteTvShows()

    @get:Rule
    var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.nav_catalogue)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovies.size))
    }

    @Test
    fun loadMovieDetail() {
        onView(withId(R.id.nav_catalogue)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(withText(dummyMovies[0].title)))
        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview)).check(matches(withText(dummyMovies[0].overview)))

        onView(withId(R.id.action_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.action_favorite)).perform(click())
    }

    @Test
    fun loadTvShow() {
        onView(withId(R.id.nav_catalogue)).check(matches(isDisplayed()))
        onView(withId(R.id.view_pager)).check(matches(isDisplayed()))
        onView(withId(R.id.view_pager)).perform(swipeLeft())
        onView(withId(R.id.rv_tvShows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvShows)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShows.size))
    }

    @Test
    fun loadTvShowDetail() {
        onView(withId(R.id.nav_catalogue)).check(matches(isDisplayed()))
        onView(withId(R.id.view_pager)).check(matches(isDisplayed()))
        onView(withId(R.id.view_pager)).perform(swipeLeft())
        onView(withId(R.id.rv_tvShows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvShows)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
//        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
//        onView(withId(R.id.tv_title)).check(matches(withText(dummyTvShows[0].name)))
//        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))
//        onView(withId(R.id.tv_overview)).check(matches(withText(dummyTvShows[0].overview)))
//
//        onView(withId(R.id.action_favorite)).check(matches(isDisplayed()))
//        onView(withId(R.id.action_favorite)).perform(click())
    }

    @Test
    fun loadFavoriteMovies() {
        onView(withId(R.id.nav_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.nav_favorite)).perform(click())
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovies.size))
    }

    @Test
    fun loadFavoriteTvShows() {
        onView(withId(R.id.nav_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.nav_favorite)).perform(click())
        onView(withId(R.id.view_pager)).perform(swipeLeft())
        onView(withId(R.id.rv_tvShows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvShows)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShows.size))
    }
}