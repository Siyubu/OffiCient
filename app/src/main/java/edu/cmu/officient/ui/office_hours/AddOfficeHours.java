/*
 *
 *  * @author Segla Boladji Vinny Trinite Adjibi
 *  * AndrewID : vadjibi
 *  * Program : MSIT
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

/*
 *
 *  * @author Segla Boladji Vinny Trinite Adjibi
 *  * AndrewID : vadjibi
 *  * Program : MSIT
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

package edu.cmu.officient.ui.office_hours;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import edu.cmu.officient.R;
import edu.cmu.officient.logic.ApplicationManager;
import edu.cmu.officient.model.Course;
import edu.cmu.officient.networktasks.RequestTaskFactory;
import edu.cmu.officient.networktasks.StandardRequestTask;
import edu.cmu.officient.util.DateConversion;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class AddOfficeHours extends Fragment {
    private AppCompatActivity activity;
    Course selected_course;
    private ProgressBar progressBar;
    private EditText start_time, end_time, venue, description;
    private Button add_course_btn;

    public AddOfficeHours(AppCompatActivity activity, Course selected_course)
    {
        this.activity=activity;
        this.selected_course=selected_course;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_add_office_hours, container, false);
       progressBar =view.findViewById(R.id.load);
       start_time = view.findViewById(R.id.start_time);
       end_time = view.findViewById(R.id.end_time);
       venue = view.findViewById(R.id.venue);
       description = view.findViewById(R.id.description);
       add_course_btn = view.findViewById(R.id.add_office_btn);
       add_course_btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               SimpleDateFormat format = new SimpleDateFormat("", Locale.ENGLISH);
               DateConversion converter = new DateConversion();
               String date = converter.getStringDateTime(new Date(), "-");
               StandardRequestTask task = RequestTaskFactory.getTask(progressBar, view, activity, null, "addOfficeHours");
               /*if (task != null)
                   task.execute("addOfficeHours",description.getText().toString(),venue.getText().toString(),start_time.getText().toString(),
                           end_time.getText().toString(), selected_course.getId() + "", "" + ApplicationManager.getInstance(activity).getLoggedInUser().getAndrewId());
*/               // Adding with Firestore

               /*FirebaseApp.initializeApp(activity);*/
               FirebaseFirestore db = FirebaseFirestore.getInstance();
               Map<String, Object> oh = new HashMap<>();

               oh.put("description", description.getText().toString());
               oh.put("venue", venue.getText().toString());
               oh.put("start_time", start_time.getText().toString());
               oh.put("end_time", end_time.getText().toString());
               oh.put("owner", ApplicationManager.getInstance(activity).getLoggedInUser().getAndrewId());
               oh.put("course_id", selected_course.getId() + "");

               db.collection("office_hours")
                       .add(oh)
                       .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                           @Override
                           public void onSuccess(DocumentReference documentReference) {
                               Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                           }
                       })
                       .addOnFailureListener(new OnFailureListener() {
                           @Override
                           public void onFailure(@NonNull Exception e) {
                               Log.w(TAG, "Error adding document", e);
                           }
                       });
           }
       });
        start_time.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b)
                    calendar_time(start_time);
            }
        });
        end_time.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b)
                    calendar_time(end_time);
            }
        });

        return view;
    }

    private void calendar_time(final EditText editText)
    {
        // calender class's instance and get current date , month and year from calender
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        // date picker dialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(activity,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // set day of month , month and year value in the edit text
                        editText.setText(getString(R.string.date_format, year, monthOfYear+1, dayOfMonth));
                        //editText.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        getTime(editText);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
    private void getTime (final EditText editText) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(activity, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                // Append to the edittext
                editText.append(" " + getString(R.string.hour_format, i, i1,0) );

            }
        }, hour, minute, true);
        timePickerDialog.show();
    }
}
