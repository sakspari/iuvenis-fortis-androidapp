package com.example.hotel.view.auth;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.example.hotel.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AuthActivityTest2 {

    @Rule
    public ActivityTestRule<AuthActivity> mActivityTestRule = new ActivityTestRule<>(AuthActivity.class);

    @Test
    public void authActivityTest2() {
        ViewInteraction materialButton = onView(
                allOf(withId(R.id.btnRegister), withText("Sign Up"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                3)),
                                1),
                        isDisplayed()));
        materialButton.perform(click());
        onView(isRoot()).perform(waitFor(3000));

        ViewInteraction textInputEditText = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.etUsername),
                                0),
                        1),
                        isDisplayed()));
        textInputEditText.perform(replaceText("marcels"), closeSoftKeyboard());



        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.btnSignUp), withText("Sign Up"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        2),
                                1),
                        isDisplayed()));
        materialButton2.perform(click());
        onView(isRoot()).perform(waitFor(3000));

        ViewInteraction textInputEditText2 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.etEmail),
                                0),
                        1),
                        isDisplayed()));
        textInputEditText2.perform(replaceText("marcels"), closeSoftKeyboard());



        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.btnSignUp), withText("Sign Up"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        2),
                                1),
                        isDisplayed()));
        materialButton3.perform(click());
        onView(isRoot()).perform(waitFor(3000));

        ViewInteraction textInputEditText3 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.etPassword),
                                0),
                        1),
                        isDisplayed()));
        textInputEditText3.perform(replaceText("mar"), closeSoftKeyboard());



        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.btnSignUp), withText("Sign Up"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        2),
                                1),
                        isDisplayed()));
        materialButton4.perform(click());
        onView(isRoot()).perform(waitFor(3000));

        ViewInteraction textInputEditText4 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.etConfirmPassword),
                                0),
                        1),
                        isDisplayed()));
        textInputEditText4.perform(replaceText("mar"), closeSoftKeyboard());



        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.btnSignUp), withText("Sign Up"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        2),
                                1),
                        isDisplayed()));
        materialButton5.perform(click());
        onView(isRoot()).perform(waitFor(3000));

        ViewInteraction textInputEditText5 = onView(
                allOf(withText("marcels"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.etEmail),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText5.perform(replaceText("marcels@gmail.com"));

        ViewInteraction textInputEditText6 = onView(
                allOf(withText("marcels@gmail.com"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.etEmail),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText6.perform(closeSoftKeyboard());



        ViewInteraction materialButton6 = onView(
                allOf(withId(R.id.btnSignUp), withText("Sign Up"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        2),
                                1),
                        isDisplayed()));
        materialButton6.perform(click());
        onView(isRoot()).perform(waitFor(3000));

        ViewInteraction textInputEditText7 = onView(
                allOf(withText("mar"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.etPassword),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText7.perform(replaceText("mar1234"));

        ViewInteraction textInputEditText8 = onView(
                allOf(withText("mar1234"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.etPassword),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText8.perform(closeSoftKeyboard());



        ViewInteraction textInputEditText9 = onView(
                allOf(withText("mar"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.etConfirmPassword),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText9.perform(replaceText("mar123"));

        ViewInteraction textInputEditText10 = onView(
                allOf(withText("mar123"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.etConfirmPassword),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText10.perform(closeSoftKeyboard());



        ViewInteraction materialButton7 = onView(
                allOf(withId(R.id.btnSignUp), withText("Sign Up"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        2),
                                1),
                        isDisplayed()));
        materialButton7.perform(click());
        onView(isRoot()).perform(waitFor(3000));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

    public static ViewAction waitFor(long delay) {
        return new ViewAction() {
            @Override public Matcher<View> getConstraints() {
                return isRoot();
            }
            @Override public String getDescription() {
                return "wait for " + delay + "milliseconds";
            }
            @Override public void perform(UiController uiController,
                                          View view) {
                uiController.loopMainThreadForAtLeast(delay);
            }
        }; }
}
