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

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import edu.cmu.officient.DBCommunication.RequestData;
import edu.cmu.officient.R;
import edu.cmu.officient.logic.ApplicationManager;
import edu.cmu.officient.model.Course;
import edu.cmu.officient.model.User;
import edu.cmu.officient.networktaks.RoleQueryRequestTask;
import edu.cmu.officient.ui.customviews.AdvancedRecyclerView;
import edu.cmu.officient.ui.listener.AddAssignmentListener;
import edu.cmu.officient.ui.listener.AddOfficeHoursListener;
import edu.cmu.officient.ui.listener.AddTAListener;

public class CourseDetailFragment extends Fragment {
    private Course course;
    private AppCompatActivity activity;
    private Button addTA, addAssignment, enroll, viewStatistics, addTaBis, addHwBis, addOHBis;
    private ProgressBar progressBar;

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
        final User user = ApplicationManager.getInstance(activity).getLoggedInUser();
        addTA = root.findViewById(R.id.add_ta);
        addAssignment = root.findViewById(R.id.add_assignment);
        enroll = root.findViewById(R.id.enroll_in);
        viewStatistics = root.findViewById(R.id.view_statistics);
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
            //new RoleQuery().execute("userRole", user.getAndrewId(), "" + course.getId());
            new RoleQueryRequestTask(activity, root, progressBar).execute("userRole", user.getAndrewId(), "" + course.getId());
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

        //tasList.setAdapter(n);
        ohrsList.setAdapter(new OfficeHoursAdapter(activity, course.getOfficeHours()));
        hwList.setAdapter(new AssignmentAdapter(activity, course.getAssignments()));

        // Buttons
        addTaBis.setOnClickListener(new AddTAListener());
        addHwBis.setOnClickListener(new AddAssignmentListener(activity, course));
        addOHBis.setOnClickListener(new AddOfficeHoursListener());
        enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new EnrollInClass().execute("enrollStudent", user.getAndrewId(), "" + course.getId());
            }
        });

        return root;
    }

    private class EnrollInClass extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

    private class RoleQuery extends AsyncTask<String, String, String> {
        private JSONObject jsonObject;
        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... args) {
            String message;
            String[] attributes = new String[] {"userRole", "andrewId", "course_id"};
            RequestData requestData = new RequestData( activity,"http://gamfruits.com/officient_api/functions.php", attributes, args);
            jsonObject = requestData.getResponse();
            System.out.println(jsonObject);
            if(jsonObject!=null){
                try {
                    message = jsonObject.getString("message");
                } catch (JSONException e) {
                    message = "error";
                    e.printStackTrace();
                }
            }
            else {
                message = "error";
            }
            return message;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            String role = "";
            if (result.equalsIgnoreCase("success")){
                try {
                    role = jsonObject.getString("data");
                } catch (JSONException e) {
                    e.printStackTrace();
                    role = "Student";
                }
                if (role.equals("Student")) {
                    enroll.setVisibility(View.VISIBLE);
                    viewStatistics.setVisibility(View.VISIBLE);
                    addHwBis.setVisibility(View.INVISIBLE);
                    addOHBis.setVisibility(View.INVISIBLE);
                }
                else if (role.equals("TA")){ // Should be TA then
                    addAssignment.setVisibility(View.VISIBLE);
                    enroll.setVisibility(View.GONE);
                }
            }
            // If failed, it will display the default button
            else if (result.equalsIgnoreCase("failed")) {
                addOHBis.setVisibility(View.INVISIBLE);
                addHwBis.setVisibility(View.INVISIBLE);
                addAssignment.setVisibility(View.INVISIBLE);
            }
            else if(result.equalsIgnoreCase("error")){
                Toast.makeText(activity, R.string.network_unavailable, Toast.LENGTH_SHORT).show();
            }
            else if (result.equalsIgnoreCase("no_data")){
                Toast.makeText(activity, R.string.data_empty, Toast.LENGTH_SHORT).show();
            }
            progressBar.setVisibility(View.GONE);
        }
    }
}
