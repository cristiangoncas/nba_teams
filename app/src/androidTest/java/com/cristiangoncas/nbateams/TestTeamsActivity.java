package com.cristiangoncas.nbateams;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.cristiangoncas.nbateams.ui.views.TeamsActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TestTeamsActivity {

    private static final String TEAM_NAME = "Boston Celtics";

    @Rule
    public ActivityTestRule<TeamsActivity> activityTestRule = new ActivityTestRule<>(TeamsActivity.class);

    @Before
    public void setUp() {
        activityTestRule.getActivity().showListTeamsView();
    }

    @Test
    public void testRecyclerTeamsDisplayed() {
        onView(withId(R.id.recycler_teams)).check(matches(isDisplayed()));
    }

    @Test
    public void testRecyclerTeamsFirstItemClick() {
//        onView(withId(R.id.recycler_teams)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }

}
