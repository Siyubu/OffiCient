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

import org.json.JSONException;
import org.json.JSONObject;

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
    private EditText due_time;
    private ProgressBar progressBar;
    private Context context;
    private ArrayAdapter adapter;
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
        due_time=findViewById(R.id.exp_time);

        courses = new ArrayList<>();
        adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, courses);
        selectCourse.setAdapter(adapter);

        addAssignmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedCourse!=null) {
                    new AddAssignment().execute("addAssignment",assign_title.getText().toString(),expect_time.getText().toString(),due_time.getText().toString(), selectedCourse.getId() + "");
                }
                else{
                    Toast.makeText(context, "Please select a course for the assignment", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private class AddAssignment extends AsyncTask<String, String, String> {
        private JSONObject jsonObject;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String[] args) {
            String message=" ";


//            String[] attributes = new String[]{"addCourse", "code", "title", "term_id"};
//            RequestData requestData = new RequestData(context, "http://gamfruits.com/officient_api/functions.php", attributes, args);
//            jsonObject = requestData.getResponse();
//            System.out.println(jsonObject);
//            try {
//                message = jsonObject.getString("message");
//            } catch (JSONException e) {
//                message = "error";
//            }
            return message;
        }
    }
}
