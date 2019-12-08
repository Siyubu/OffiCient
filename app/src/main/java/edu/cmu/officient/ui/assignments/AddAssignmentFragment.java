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
import android.widget.Toast;

import com.google.zxing.WriterException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import edu.cmu.officient.DBCommunication.RequestData;
import edu.cmu.officient.R;
import edu.cmu.officient.api.qrcode.QRImageGenerator;
import edu.cmu.officient.model.Assignment;
import edu.cmu.officient.model.Course;

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
        View view = inflater.inflate(R.layout.fragment_add_assigment, container, false);
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
                new AddAssignment().execute("addAssignment",assign_title.getText().toString(),deadline.getText().toString(),new Date().toString(),
                            availability.getText().toString(),expect_time.getText().toString(), selectedCourse.getId() + "");
                Toast.makeText(activity, availability.getText()+"", Toast.LENGTH_SHORT).show();
                Toast.makeText(activity, new Date().toString()+"", Toast.LENGTH_SHORT).show();
            }
        });
        deadline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar_time(deadline);

            }
        });
        availability.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar_time(availability);

            }
        });

        return view;
    }

    private class AddAssignment extends AsyncTask<String, String, String>
    {
        private JSONObject jsonObject;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String[] args)
        {
            String message=" ";
            String[] attributes = new String[]{"addAssignment", "title", "deadline", "published_on", "availability","expected_time","course_id"};
            RequestData requestData = new RequestData(activity, "http://gamfruits.com/officient_api/functions.php", attributes, args);
            jsonObject = requestData.getResponse();
            System.out.println(jsonObject);
            try {
                message = jsonObject.getString("message");
            } catch (JSONException e) {
                message = "error";
            }
            return message;
        }
        @Override
        protected void onPostExecute(String result)
        {
            progressBar.setVisibility(View.GONE);
            if (result.equalsIgnoreCase("success")){
                Toast.makeText(activity, "Assignment added Successfully", Toast.LENGTH_LONG).show();
            }
            else if(result.equalsIgnoreCase("error")){
                Toast.makeText(activity, "Unable to connect to the internet. Assignment not added", Toast.LENGTH_SHORT).show();
            }
            else if (result.equalsIgnoreCase("failed")){
                Toast.makeText(activity, "Problem with App. Contact admin.", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void calendar_time(final EditText editText)
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
                        editText.setText(dayOfMonth + "/"
                                + (monthOfYear + 1) + "/" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();

    }

}
