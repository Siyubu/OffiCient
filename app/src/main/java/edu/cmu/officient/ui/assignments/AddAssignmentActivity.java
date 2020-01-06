/*
 *
 *  * @author Segla Boladji Vinny Trinite Adjibi
 *  * AndrewID : vadjibi
 *  * Program : MSIT
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *  
 */

package edu.cmu.officient.ui.assignments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import edu.cmu.officient.R;
import edu.cmu.officient.model.Course;
import edu.cmu.officient.networktasks.RequestTaskFactory;
import edu.cmu.officient.networktasks.StandardRequestTask;
import edu.cmu.officient.util.DateConversion;

public class AddAssignmentActivity extends AppCompatActivity {

    private Button addAssignmentBtn;
    private EditText assign_title;
    private EditText expect_time;
    private EditText deadline;
    private EditText availability;
    private ProgressBar progressBar;
    Course selectedCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assignment);
        getSupportActionBar().setTitle(R.string.add_assignment);

        Intent intent = getIntent();
        selectedCourse = (Course) intent.getSerializableExtra("course");

        progressBar =findViewById(R.id.load);
        addAssignmentBtn=findViewById(R.id.add_course);
        assign_title=findViewById(R.id.ass_title);
        expect_time=findViewById(R.id.exp_time);
        deadline=findViewById(R.id.due_date);
        availability=findViewById(R.id.avail_time);

        addAssignmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                SimpleDateFormat format = new SimpleDateFormat("", Locale.ENGLISH);
                DateConversion converter = new DateConversion();
                String date = converter.getStringDateTime(new Date(), "-");
                StandardRequestTask task = RequestTaskFactory.getTask(progressBar, null, AddAssignmentActivity.this, null, "addAssignment");
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

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("course", selectedCourse);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        selectedCourse = (Course) savedInstanceState.getSerializable("course");
    }

    private void calendar_time(final EditText editText)
    {
        // calender class's instance and get current date , month and year from calender
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        // date picker dialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
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
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                // Append to the edittext
                editText.append(" " + getString(R.string.hour_format, i, i1,0) );

            }
        }, hour, minute, true);
        timePickerDialog.show();
    }

}
