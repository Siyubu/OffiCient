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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import edu.cmu.officient.R;
import edu.cmu.officient.logic.ApplicationManager;
import edu.cmu.officient.model.Course;
import edu.cmu.officient.model.User;
import edu.cmu.officient.networktasks.RequestTaskFactory;
import edu.cmu.officient.networktasks.StandardRequestTask;
import edu.cmu.officient.ui.assignments.AssignmentAdapter;
import edu.cmu.officient.ui.customviews.AdvancedRecyclerView;
import edu.cmu.officient.ui.listener.AddAssignmentListener;
import edu.cmu.officient.ui.listener.AddOfficeHoursListener;
import edu.cmu.officient.ui.listener.AddTAListener;
import edu.cmu.officient.ui.office_hours.OfficeHoursAdapter;
import edu.cmu.officient.util.ModelObjectBuilder;

public class CourseDetailActivity extends AppCompatActivity {
    private Course course;
    private ProgressBar progressBar;
    AdvancedRecyclerView ohrsList;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        course = (Course) intent.getSerializableExtra("course");
        setContentView(R.layout.activity_course_detail);

        getSupportActionBar().setTitle(course.getTitle());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        progressBar = findViewById(R.id.progress_bar);
        TextView courseName = findViewById(R.id.course_name), term = findViewById(R.id.term);
        courseName.setText(getString(R.string.tmpl_course_name, course.getCode(), course.getTitle()));
        term.setText(course.getTerm().toString());
        final User user = ApplicationManager.getInstance(this).getLoggedInUser();
        Button addTA, enroll, addTaBis, addHwBis, addOHBis;
        addTA = findViewById(R.id.add_ta);
        enroll = findViewById(R.id.enroll_in);
        addTaBis = findViewById(R.id.add_ta_btn);
        addHwBis = findViewById(R.id.add_assignment_btn);
        addOHBis = findViewById(R.id.add_office_hours_btn);

        if (user.isFaculty()) {
            enroll.setVisibility(View.GONE);
            addTA.setVisibility(View.VISIBLE);
        }
        else {
            // Should depend on what's the user status with the course
            // Make a query to get the user role
            findViewById(R.id.ta_container).setVisibility(View.GONE); // Others should not see this
            StandardRequestTask task = RequestTaskFactory.getTask(progressBar, findViewById(android.R.id.content).getRootView(), this, null, "userRole");
            if (task != null)
                task.execute("userRole", user.getAndrewId(), "" + course.getId());
        }

        // Set the recycler views
        AdvancedRecyclerView tasList = findViewById(R.id.tas_recycler), hwList = findViewById(R.id.assignment_recycler);
        ohrsList = findViewById(R.id.oh_recycler);
        RecyclerView.LayoutManager llm1 = new LinearLayoutManager(this), llm2 = new LinearLayoutManager(this), llm3 = new LinearLayoutManager(this);
        tasList.setLayoutManager(llm1);
        ohrsList.setLayoutManager(llm2);
        hwList.setLayoutManager(llm3);
        tasList.setEmptyView(findViewById(R.id.tas_not_found));
        ohrsList.setEmptyView(findViewById(R.id.oh_not_found));
        hwList.setEmptyView(findViewById(R.id.hw_not_found));

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("office_hours")
                .whereEqualTo("course_id", "" + course.getId())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(getClass().getSimpleName(), document.getId() + " => " + document.getData());
                                course.addOfficeHours(ModelObjectBuilder.buildOfficeHour(document.getId(), document.getData()));
                            }
                            ohrsList.setAdapter(new OfficeHoursAdapter( CourseDetailActivity.this, course.getOfficeHours()));
                        } else {
                            Log.d(getClass().getSimpleName(), "Error getting documents: ", task.getException());
                        }
                    }
                });

        hwList.setAdapter(new AssignmentAdapter(this, course.getAssignments()));

        // Buttons
        addTA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddTAListener(CourseDetailActivity.this, course);
            }
        });
        addTaBis.setOnClickListener(new AddTAListener(this, course));
        addHwBis.setOnClickListener(new AddAssignmentListener(this, course));
        addOHBis.setOnClickListener(new AddOfficeHoursListener(this,course));
        enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StandardRequestTask task = RequestTaskFactory.getTask(progressBar, getWindow().getDecorView().getRootView(), CourseDetailActivity.this, null, "enrollStudent");
                if (task != null)
                    task.execute("enrollStudent", user.getAndrewId(), "" + course.getId());
            }
        });

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("course", course);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        course = (Course) savedInstanceState.getSerializable("course");
    }
}
