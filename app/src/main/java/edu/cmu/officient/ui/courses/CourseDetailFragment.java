/*
 *
 *  * @author Segla Boladji Vinny Trinite Adjibi
 *  * AndrewID : vadjibi
 *  * Program : MSIT
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */
package edu.cmu.officient.ui.courses;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import edu.cmu.officient.R;
import edu.cmu.officient.logic.ApplicationManager;
import edu.cmu.officient.model.Course;
import edu.cmu.officient.model.User;
import edu.cmu.officient.ui.customviews.AdvancedRecyclerView;
import edu.cmu.officient.ui.listener.AddAssignmentListener;
import edu.cmu.officient.ui.listener.AddOfficeHoursListener;
import edu.cmu.officient.ui.listener.AddTAListener;

public class CourseDetailFragment extends Fragment {
    private Course course;
    private AppCompatActivity activity;
    ProgressBar progressBar;

    public CourseDetailFragment(AppCompatActivity activity, Course course) {
        this.activity = activity;
        this.course = course;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity.getSupportActionBar().setTitle(R.string.course_details_label);

        View root = inflater.inflate(R.layout.fragment_course_detail, container, false);
        progressBar = root.findViewById(R.id.progress_bar);
        TextView courseName = root.findViewById(R.id.course_name), term = root.findViewById(R.id.term);
        courseName.setText(getString(R.string.tmpl_course_name, course.getCode(), course.getTitle()));
        term.setText(course.getTerm().toString());
        User user = ApplicationManager.getInstance(activity).getLoggedInUser();
        Button addTA = root.findViewById(R.id.add_ta), addAssignment = root.findViewById(R.id.add_assignment),
                enroll = root.findViewById(R.id.enroll_in), viewStatistics = root.findViewById(R.id.view_statistics);

        if (user.isFaculty()) {
            enroll.setVisibility(View.GONE);
            addTA.setVisibility(View.VISIBLE);
        }
        else {
            // Should depend on what's the user status with the course
            // Make a query to get the user role
            final String [] parameters = new String[] {""+ user.getId(), "" + course.getId()};
        }

        // Set the recyclcer views
        AdvancedRecyclerView tasList = root.findViewById(R.id.tas_recycler), ohrsList = root.findViewById(R.id.oh_recycler), hwList = root.findViewById(R.id.assignment_recycler);
        RecyclerView.LayoutManager llm1 = new LinearLayoutManager(activity), llm2 = new LinearLayoutManager(activity), llm3 = new LinearLayoutManager(activity);
        tasList.setLayoutManager(llm1);
        ohrsList.setLayoutManager(llm2);
        hwList.setLayoutManager(llm3);
        tasList.setEmptyView(root.findViewById(R.id.tas_not_found));
        ohrsList.setEmptyView(root.findViewById(R.id.oh_not_found));
        hwList.setEmptyView(root.findViewById(R.id.hw_not_found));

        //tasList.setAdapter(n);
        ohrsList.setAdapter(new OfficeHoursAdapter(activity, course.getOfficeHours()));
        hwList.setAdapter(new AssignmentAdapter(activity, course.getAssignments()));

        // Buttons
        Button addTaBis = root.findViewById(R.id.add_ta_btn), addHwBis = root.findViewById(R.id.add_assignment_btn), addOHBis = root.findViewById(R.id.add_office_hours_btn);
        addTaBis.setOnClickListener(new AddTAListener());
        addHwBis.setOnClickListener(new AddAssignmentListener(activity));
        addOHBis.setOnClickListener(new AddOfficeHoursListener());
        return root;
    }

    private class RoleQuery extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setVisibility(View.GONE);
        }
    }
}
