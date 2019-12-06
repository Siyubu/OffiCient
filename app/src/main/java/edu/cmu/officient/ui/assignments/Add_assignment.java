/*
 *
 *  * @author Solange Iyubu
 *  * AndrewID :siyubu
 *  * Program : ECE
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

package edu.cmu.officient.ui.assignments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.ArrayList;

import edu.cmu.officient.DBCommunication.RequestData;
import edu.cmu.officient.R;
import edu.cmu.officient.model.Course;
import edu.cmu.officient.model.Term;

public class Add_assignment extends AppCompatActivity {
    private Spinner selectCourse;
    private Button addAssignmentBtn;
    private EditText assign_title;
    private EditText expect_time;
    private EditText deadline;
    private EditText availability;
    private ProgressBar progressBar;
    private Context context;
    private ArrayAdapter adapter;
    private LocalDateTime posted_on;
    ArrayList<Course> courses;
    Term selectedCourse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assignment2);

        context = this;
        progressBar = findViewById(R.id.load);

        selectCourse=findViewById(R.id.select_course);
        addAssignmentBtn=findViewById(R.id.add_course);
        assign_title=findViewById(R.id.ass_title);
        expect_time=findViewById(R.id.exp_time);
        deadline=findViewById(R.id.due_date);
        availability=findViewById(R.id.avail_time);

        courses = new ArrayList<>();
        adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, courses);
        selectCourse.setAdapter(adapter);

        addAssignmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedCourse!=null) {
                    new AddAssignment().execute("addAssignment",assign_title.getText().toString(),deadline.getText().toString(),posted_on.toString(),
                         availability.getText().toString(),expect_time.getText().toString(), selectedCourse.getId() + "");
                }
                else{
                    Toast.makeText(context, "Please select a course for the assignment", Toast.LENGTH_SHORT).show();
                }
            }
        });

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
            String[] attributes = new String[]{"addAssignment", "title", "deadline", "published_on", "avalability","expected_time","course_id"};
            RequestData requestData = new RequestData(context, "http://gamfruits.com/officient_api/functions.php", attributes, args);
            jsonObject = requestData.getResponse();
            System.out.println(jsonObject);
            try {
                message = jsonObject.getString("message");
            } catch (JSONException e) {
                message = "error";
            }
            return message;
        }

        protected void onPostExecute(String result){
            progressBar.setVisibility(View.GONE);
            if (result.equalsIgnoreCase("success")){
                Toast.makeText(context, "Assignment added Successfully", Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(context, CoursesList.class);
//                startActivity(intent);
            }
            else if(result.equalsIgnoreCase("error")){
                Toast.makeText(context, "Unable to connect to the internet. Assignment not added", Toast.LENGTH_SHORT).show();
            }
            else if (result.equalsIgnoreCase("failed")){
                Toast.makeText(context, "Problem with App. Contact admin.", Toast.LENGTH_LONG).show();
            }
        }
    }






    private class CourseList extends AsyncTask<String, String, String>
    {
        private JSONObject jsonObject;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String[] args) {
            String message;
            String[] attributes = new String[]{"courses"};
            RequestData requestData = new RequestData( context,"http://gamfruits.com/officient_api/functions.php", attributes, args);
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

        protected void onPostExecute(String result){
            progressBar.setVisibility(View.GONE);
            System.out.println(result);
            edu.cmu.officient.util.DateConversion dateConversion = new edu.cmu.officient.util.DateConversion();
            if (result.equalsIgnoreCase("success")){
                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    JSONObject row;
                    for(int i=0;i<jsonArray.length();i++){
                        row = (JSONObject) jsonArray.get(i);
//                        courses.add(new Course(row.getInt("id"), row.getString("title"),
//                                dateConversion.stringToDate(row.getString("start_date")),
//                                dateConversion.stringToDate(row.getString("end_date"))));
//                        //items.add(row.getString("name") +" ( From " +row.getString("start_date")+" to "+row.getString("end_date")+")");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast.makeText(context, "Term List Retrieved", Toast.LENGTH_SHORT).show();
            }
            else if(result.equalsIgnoreCase("error")){
                Toast.makeText(context, "Unable to connect to the internet. The term list won't be updated", Toast.LENGTH_SHORT).show();
            }
            else if (result.equalsIgnoreCase("no_data")){
                //items.add("Term list empty");
                Toast.makeText(context, "term list empty", Toast.LENGTH_SHORT).show();
            }
            adapter.notifyDataSetChanged();

        }
    }

}

