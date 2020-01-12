/*
 *
 *  * @author Segla Boladji Vinny Trinite Adjibi
 *  * AndrewID : vadjibi
 *  * Program : MSIT
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

package edu.cmu.officient.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.cmu.officient.R;
import edu.cmu.officient.logic.ApplicationManager;
import edu.cmu.officient.model.Course;
import edu.cmu.officient.model.User;
import edu.cmu.officient.networktasks.CoursesListTask;
import edu.cmu.officient.networktasks.RequestTaskFactory;
import edu.cmu.officient.networktasks.StandardRequestTask;

public class DashboardFragment extends Fragment {
    private AppCompatActivity activity;
    private List<Course> courses = new ArrayList<>();
    private Spinner spinner;
    private ProgressBar progressBar;

    public DashboardFragment(AppCompatActivity activity) {
        this.activity = activity;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        progressBar = root.findViewById(R.id.progress_bar);
        Button submit = root.findViewById(R.id.submit_action);
        spinner = root.findViewById(R.id.courses_selection);

        final User user = ApplicationManager.getInstance(activity).getLoggedInUser();

        String [] args;
        if (user.isFaculty())
            args = new String[] {"coursesListSpinner", "" + user.getId()};
        else
            args = new String[] {"coursesListSpinner"};

        new CoursesListTask(activity, root, progressBar, args.length, spinner, courses).execute(args);

        submit.setOnClickListener(new View.OnClickListener() {
            private String ass_line, ohrs_line;
            private StringBuilder ohRecords;
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                // Get the element selected
                Course course = courses.get(spinner.getSelectedItemPosition());
                int course_id = course.getId();
                Log.e("ERR", "Course is " + course_id);
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                CollectionReference assignments = db.collection("assignment_records"), ohrs = db.collection("office_hours_records");
                assignments.whereEqualTo("course_id", course_id);
                if ( !user.isFaculty())
                    assignments.whereEqualTo("user", user.getAndrewId());

                assignments.get()
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                e.printStackTrace();
                            }
                        })
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                StringBuilder builder = new StringBuilder("Name;Andrew Id;Start Date; End Date");
                                for (DocumentSnapshot snapshot : queryDocumentSnapshots) {
                                    builder.append(snapshot.getString("title"));
                                    builder.append(";");
                                    builder.append(snapshot.getString("user"));
                                    builder.append(";");
                                    builder.append(snapshot.getDate("started_at"));
                                    builder.append(";");
                                    builder.append(snapshot.getDate("ended_at"));
                                    builder.append("\n");
                                }
                                ass_line = builder.toString();
                            }
                        });

                // Office hours here
                ohRecords = new StringBuilder();
                if (user.isFaculty()) {
                    ohrs.get()
                            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                    StringBuilder builder = new StringBuilder("Name;Andrew Id;Start Date; End Date");
                                    for (DocumentSnapshot snapshot : queryDocumentSnapshots) {
                                        builder.append(snapshot.getString("title"));
                                        builder.append(";");
                                        builder.append(snapshot.getString("user"));
                                        builder.append(";");
                                        builder.append(snapshot.getDate("started_at"));
                                        builder.append(";");
                                        builder.append(snapshot.getDate("ended_at"));
                                        builder.append("\n");
                                    }
                                    ohrs_line = builder.toString();
                                }
                            });
                }
            }
        });
        return root;
    }
}