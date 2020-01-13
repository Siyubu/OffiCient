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

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
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


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
                             final ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        progressBar = root.findViewById(R.id.progress_bar);
        final Button submit = root.findViewById(R.id.submit_action);
        spinner = root.findViewById(R.id.courses_selection);
        final int MAX_FETCH;

        final User user = ApplicationManager.getInstance(activity).getLoggedInUser();

        String [] args;
        if (user.isFaculty()) {
            args = new String[] {"coursesListSpinner", "" + user.getId()};
            MAX_FETCH = 2;
        }
        else {
            args = new String[] {"coursesListSpinner"};
            MAX_FETCH = 3;
        }
        new CoursesListTask(activity, root, progressBar, args.length, spinner, courses).execute(args);

        submit.setOnClickListener(new View.OnClickListener() {
            private String ass_line, ohrs_line, ohrs_ta;
            private int completed = 0;
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                submit.setEnabled(false);
                // Get the element selected
                Course course = courses.get(spinner.getSelectedItemPosition());
                int course_id = course.getId();
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                CollectionReference assignments = db.collection("assignment_records"), ohrs = db.collection("office_hours_records");
                assignments.whereEqualTo("course_id", course_id);
                if ( ! user.isFaculty()) {
                    assignments.whereEqualTo("user", user.getAndrewId())
                            .get()
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    e.printStackTrace();
                                }
                            })
                            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                    StringBuilder builder = new StringBuilder("title;student_id;started_at;ended_at\n");
                                    for (DocumentSnapshot snapshot : queryDocumentSnapshots) {
                                        builder.append("\"");
                                        builder.append(snapshot.getString("title"));
                                        builder.append("\"");
                                        builder.append(",");
                                        builder.append(snapshot.getString("user"));
                                        builder.append(",");
                                        builder.append(snapshot.getDate("started_at"));
                                        builder.append(",");
                                        builder.append(snapshot.getDate("ended_at"));
                                        builder.append("\n");
                                    }
                                    ass_line = builder.toString();
                                    addCompleted();
                                }
                            });
                }
                else {
                    assignments
                            .get()
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    e.printStackTrace();
                                }
                            })
                            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                    StringBuilder builder = new StringBuilder("title;student_id;started_at;ended_at\n");
                                    for (DocumentSnapshot snapshot : queryDocumentSnapshots) {
                                        builder.append("\"");
                                        builder.append(snapshot.getString("title"));
                                        builder.append("\"");
                                        builder.append(",");
                                        builder.append(snapshot.getString("user"));
                                        builder.append(",");
                                        builder.append(snapshot.getDate("started_at"));
                                        builder.append(",");
                                        builder.append(snapshot.getDate("ended_at"));
                                        builder.append("\n");
                                    }
                                    ass_line = builder.toString();
                                    addCompleted();
                                }
                            });
                }

                // Office hours here
                if (user.isFaculty()) {
                    ohrs.whereEqualTo("course_id", course_id)
                            .get()
                            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                    StringBuilder ohRecords = new StringBuilder("student;instructor;started_at;ended_at\n");
                                    for (DocumentSnapshot snapshot : queryDocumentSnapshots) {
                                            ohRecords.append(snapshot.getString("user"));
                                            ohRecords.append(",");
                                            ohRecords.append(snapshot.getString("held_by"));
                                            ohRecords.append(",");
                                            ohRecords.append(snapshot.getDate("started_at"));
                                            ohRecords.append(",");
                                            ohRecords.append(snapshot.getDate("ended_at"));
                                            ohRecords.append("\n");
                                    }
                                    ohrs_line = ohRecords.toString();
                                    addCompleted();
                                }
                            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            e.printStackTrace();
                        }
                    });
                }
                else {
                    // User is the student
                    db.collection("office_hours_records")
                            .whereEqualTo("course_id", "" + course_id)
                            .whereEqualTo("user", user.getAndrewId())
                            .get()
                            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                    StringBuilder ohRecords = new StringBuilder("student;instructor;started_at;ended_at\n");
                                    for (DocumentSnapshot snapshot : queryDocumentSnapshots) {
                                        ohRecords.append(snapshot.getString("user"));
                                        ohRecords.append(",");
                                        ohRecords.append(snapshot.getString("held_by"));
                                        ohRecords.append(",");
                                        ohRecords.append(snapshot.getDate("started_at"));
                                        ohRecords.append(",");
                                        ohRecords.append(snapshot.getDate("ended_at"));
                                        ohRecords.append("\n");
                                    }
                                    ohrs_line = ohRecords.toString();
                                    addCompleted();
                                }
                            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            e.printStackTrace();
                        }
                    })
                    ;

                    // User is TA
                    db.collection("office_hours_records")
                            .whereEqualTo("course_id", "" + course_id)
                            .whereEqualTo("held_by", user.getAndrewId())
                            .get()
                            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                    StringBuilder ohRecords = new StringBuilder("student;instructor;started_at;ended_at\n");
                                    if ( ! queryDocumentSnapshots.isEmpty()) {
                                        for (DocumentSnapshot snapshot : queryDocumentSnapshots) {
                                            ohRecords.append(snapshot.getString("user"));
                                            ohRecords.append(",");
                                            ohRecords.append(snapshot.getString("held_by"));
                                            ohRecords.append(",");
                                            ohRecords.append(snapshot.getDate("started_at"));
                                            ohRecords.append(",");
                                            ohRecords.append(snapshot.getDate("ended_at"));
                                            ohRecords.append("\n");
                                        }
                                        ohrs_ta = ohRecords.toString();
                                    }
                                    addCompleted();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            e.printStackTrace();
                        }
                    });
                }
            }
            private void addCompleted(){
                if (completed != MAX_FETCH)
                    ++completed;
                if (completed == MAX_FETCH) {
                    saveToFilesAndSend();
                    progressBar.setVisibility(View.GONE);
                }
            }

            private void saveToFilesAndSend(){
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd_hhmmss", Locale.getDefault());
                Date now = new Date();

                // ---- Assignments records
                try {
                    File filepath = activity.getExternalFilesDir("records/");
                    String directory = filepath.getAbsolutePath();
                    File aRecords = new File(directory + "/" + "assignments-" + format.format(now) + ".csv");
                    File ohRecords = new File(directory + "/" + "attended_office_hours-" + format.format(now) + ".csv");
                    File ohRecords2 = new File(directory + "/" + "held_office_hours-" + format.format(now) + ".csv");

                    aRecords.createNewFile();
                    ohRecords.createNewFile();
                    ohRecords2.createNewFile();
                    FileWriter writer = new FileWriter(aRecords), writer1 = new FileWriter(ohRecords), writer2 = new FileWriter(ohRecords2);
                    writer.write(ass_line);
                    writer.flush();
                    writer.close();

                    writer1.write(ohrs_line);
                    writer1.flush();
                    writer1.close();

                    if (ohrs_ta != null) {
                        writer2.write(ohrs_ta);
                        writer2.flush();
                        writer2.close();
                    }

                    // Here we can send the mail
                    String [] emails = new String[] {user.getEmail(), user.getAlternativeEmail()} ;
                    Intent emailIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, emails);
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Actvities report for " + courses.get(spinner.getSelectedItemPosition()).getTitle());
                    ArrayList<Uri> uris = new ArrayList<>();
                    uris.add(FileProvider.getUriForFile(activity,activity.getApplicationContext().getPackageName() + ".provider", aRecords));
                    uris.add(FileProvider.getUriForFile(activity,activity.getApplicationContext().getPackageName() + ".provider", ohRecords));
                    uris.add(FileProvider.getUriForFile(activity,activity.getApplicationContext().getPackageName() + ".provider", ohRecords2));
                    emailIntent.setType("text/csv");
                    emailIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);


                    activity.startActivity(emailIntent);

                }
                catch (IOException e) {
                    Log.e("DASHBOARD", "Unable to create or write to the temp files");
                    e.printStackTrace();
                }

            }
        });
        return root;
    }

}