package com.jwhh.jim.notekeeper;
import org.junit.Rule;
import org.junit.Test;

import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.NavigationViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static org.hamcrest.Matchers.*;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Jim.
 */
public class NextThroughNotesTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule(MainActivity.class);

    @Test
    public void NextThroughNotes() {
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_notes));

        onView(withId(R.id.list_items)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        List<NoteInfo> notes = DataManager.getInstance().getNotes();
        for(int index = 0; index < notes.size(); index++) {
            NoteInfo note = notes.get(index);

            onView(withId(R.id.spinner_courses)).check(
                    matches(withSpinnerText(note.getCourse().getTitle())));
            onView(withId(R.id.text_note_title)).check(matches(withText(note.getTitle())));
            onView(withId(R.id.text_note_text)).check(matches(withText(note.getText())));

            if(index < notes.size() - 1)
                onView(allOf(withId(R.id.action_next), isEnabled())).perform(click());
        }
        onView(withId(R.id.action_next)).check(matches(not(isEnabled())));
        pressBack();
    }
}










