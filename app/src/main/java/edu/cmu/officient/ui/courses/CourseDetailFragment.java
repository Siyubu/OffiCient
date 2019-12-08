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

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import edu.cmu.officient.R;
import edu.cmu.officient.logic.ApplicationManager;
import edu.cmu.officient.model.Course;
import edu.cmu.officient.model.User;
import edu.cmu.officient.networktasks.RequestTaskFactory;
import edu.cmu.officient.networktasks.StandardRequestTask;
import edu.cmu.officient.ui.customviews.AdvancedRecyclerView;
import edu.cmu.officient.ui.listener.AddAssignmentListener;
import edu.cmu.officient.ui.listener.AddOfficeHoursListener;
import edu.cmu.officient.ui.listener.AddTAListener;

public class CourseDetailFragment extends Fragment {
    private Course course;
    private AppCompatActivity activity;
    private ProgressBar progressBar;

    public CourseDetailFragment(AppCompatActivity activity, Course course) {
        this.activity = activity;
        this.course = course;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity.getSupportActionBar().setTitle(R.string.course_details_label);


        final View root = inflater.inflate(R.layout.fragment_course_detail, container, false);
        progressBar = root.findViewById(R.id.progress_bar);
        TextView courseName = root.findViewById(R.id.course_name), term = root.findViewById(R.id.term);
        courseName.setText(getString(R.string.tmpl_course_name, course.getCode(), course.getTitle()));
        term.setText(course.getTerm().toString());
        final User user = ApplicationManager.getInstance(activity).getLoggedInUser();
        Button addTA, enroll, addTaBis, addHwBis, addOHBis;
        addTA = root.findViewById(R.id.add_ta);
        enroll = root.findViewById(R.id.enroll_in);
        addTaBis = root.findViewById(R.id.add_ta_btn);
        addHwBis = root.findViewById(R.id.add_assignment_btn);
        addOHBis = root.findViewById(R.id.add_office_hours_btn);

        if (user.isFaculty()) {
            enroll.setVisibility(View.GONE);
            addTA.setVisibility(View.VISIBLE);
        }
        else {
            // Should depend on what's the user status with the course
            // Make a query to get the user role
            root.findViewById(R.id.ta_container).setVisibility(View.GONE); // Others should not see this
            StandardRequestTask task = RequestTaskFactory.getTask(progressBar, root, activity, null, "userRole");
            if (task != null)
                task.execute("userRole", user.getAndrewId(), "" + course.getId());
        }

        // Set the recycler views
        AdvancedRecyclerView tasList = root.findViewById(R.id.tas_recycler), ohrsList = root.findViewById(R.id.oh_recycler), hwList = root.findViewById(R.id.assignment_recycler);
        RecyclerView.LayoutManager llm1 = new LinearLayoutManager(activity), llm2 = new LinearLayoutManager(activity), llm3 = new LinearLayoutManager(activity);
        tasList.setLayoutManager(llm1);
        ohrsList.setLayoutManager(llm2);
        hwList.setLayoutManager(llm3);
        tasList.setEmptyView(root.findViewById(R.id.tas_not_found));
        ohrsList.setEmptyView(root.findViewById(R.id.oh_not_found));
        hwList.setEmptyView(root.findViewById(R.id.hw_not_found));

        ohrsList.setAdapter(new OfficeHoursAdapter(activity, course.getOfficeHours()));
        hwList.setAdapter(new AssignmentAdapter(activity, course.getAssignments()));

        // Buttons
        addTA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StandardRequestTask task = RequestTaskFactory.getTask(null, null, activity, null, "allStudents", "" + course.getId());
                if (task != null)
                    task.execute("allStudents", "" + course.getId());
            }
        });
        addTaBis.setOnClickListener(new AddTAListener(activity, course));
        addHwBis.setOnClickListener(new AddAssignmentListener(activity, course));
        addOHBis.setOnClickListener(new AddOfficeHoursListener());
        enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StandardRequestTask task = RequestTaskFactory.getTask(progressBar, root, activity, null, "enrollStudent");
                if (task != null)
                    task.execute("enrollStudent", user.getAndrewId(), "" + course.getId());
            }
        });

        return root;
    }
}
