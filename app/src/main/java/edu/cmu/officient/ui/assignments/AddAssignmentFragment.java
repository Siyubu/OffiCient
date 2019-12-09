/*
 *
 *  * @author Solange Iyubu
 *  * AndrewID : siyubu
 *  * Program : MSECE
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

package edu.cmu.officient.ui.assignments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.zxing.WriterException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import edu.cmu.officient.DBCommunication.RequestData;
import edu.cmu.officient.R;
import edu.cmu.officient.api.qrcode.QRImageGenerator;
import edu.cmu.officient.model.Assignment;
import edu.cmu.officient.model.Course;
import edu.cmu.officient.networktasks.RequestTaskFactory;
import edu.cmu.officient.networktasks.StandardRequestTask;
import edu.cmu.officient.util.DateConversion;

public class AddAssignmentFragment extends Fragment
{

   // private Spinner selectCourse;
    private Button addAssignmentBtn;
    private EditText assign_title;
    private EditText expect_time;
    private EditText deadline;
    private EditText availability;
    private ProgressBar progressBar;
   // private ArrayAdapter adapter;
   // ArrayList<Course> courses;
    private AppCompatActivity activity;
    Course selectedCourse;

    public AddAssignmentFragment(AppCompatActivity activity,Course selectedCourse)
    {
       this.activity=activity;
       this.selectedCourse=selectedCourse;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        final View view = inflater.inflate(R.layout.fragment_add_assigment, container, false);
        progressBar =view.findViewById(R.id.load);
        addAssignmentBtn=view.findViewById(R.id.add_course);
        assign_title=view.findViewById(R.id.ass_title);
        expect_time=view.findViewById(R.id.exp_time);
        deadline=view.findViewById(R.id.due_date);
        availability=view.findViewById(R.id.avail_time);

        addAssignmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                SimpleDateFormat format = new SimpleDateFormat("", Locale.getDefault());
                DateConversion converter = new DateConversion();
                String date = converter.getStringDateTime(new Date(), "-");
                StandardRequestTask task = RequestTaskFactory.getTask(progressBar, view, activity, null, "addAssignment");
                if (task != null)
                    task.execute("addAssignment",assign_title.getText().toString(),deadline.getText().toString(),date,
                            availability.getText().toString(),expect_time.getText().toString(), selectedCourse.getId() + "");
            }
        });
        deadline.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b)
                    calendar_time(deadline);
            }
        });
        availability.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b)
                    calendar_time(availability);
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
