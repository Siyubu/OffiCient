/*
 *
 *  * @author Segla Boladji Vinny Trinite Adjibi
 *  * AndrewID : vadjibi
 *  * Program : MSIT
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

package edu.cmu.officient.ui.util;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import edu.cmu.officient.R;
import edu.cmu.officient.logic.ApplicationManager;
import edu.cmu.officient.model.User;
import edu.cmu.officient.ui.courses.CoursesFragment;
import edu.cmu.officient.ui.dashboard.DashboardFragment;
import edu.cmu.officient.ui.listener.AddCourseListener;
import edu.cmu.officient.ui.listener.QRCodeScannerListener;
import edu.cmu.officient.ui.listener.TrackedActivitiesViewerListener;
import edu.cmu.officient.ui.users.ProfileFragment;

public class NavigationInitializer {
    private AppCompatActivity activity;
    private BottomNavigationView.OnNavigationItemSelectedListener navListener;

    public NavigationInitializer(final AppCompatActivity runningActivity) {
        activity = runningActivity;
        navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selected = null;
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        selected = new CoursesFragment(activity);
                        break;
                    case R.id.navigation_dashboard:
                        selected = new DashboardFragment(activity);
                        break;
                    case R.id.navigation_notifications:
                        selected = new ProfileFragment(activity);
                        break;
                }
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selected).commit();
                return true;
            }
        };
    }

    public void setUpBottomNavigationBar(BottomNavigationView navView) {
        FloatingActionButton scanCode = activity.findViewById(R.id.scan_code), viewStatus = activity.findViewById(R.id.see_activities)
                , addCourse = activity.findViewById(R.id.add_course_fab);

        scanCode.setOnClickListener(new QRCodeScannerListener(activity));
        viewStatus.setOnClickListener(new TrackedActivitiesViewerListener(activity));
        addCourse.setOnClickListener(new AddCourseListener(activity));

        // Show what should be shown and hide otherwise
        User loggedIn = ApplicationManager.getInstance(activity).getLoggedInUser();
        if (loggedIn != null && loggedIn.isFaculty()) {
            viewStatus.setVisibility(View.GONE);
            scanCode.setVisibility(View.GONE);
            addCourse.setVisibility(View.VISIBLE);
        }
        navView.setOnNavigationItemSelectedListener(navListener);
    }
}
