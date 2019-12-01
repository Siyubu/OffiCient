/*
 *
 *  * @author Wuyeh Jobe
 *  * AndrewID : jwuyeh
 *  * Program : MSIT
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

package edu.cmu.officient.ui.courses;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomappbar.BottomAppBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import edu.cmu.officient.DBCommunication.RequestData;
import edu.cmu.officient.R;

public class AddCourseActivity extends AppCompatActivity {

    private Spinner dropdown;
    private Button addCourseBtn;
    private EditText course_code;
    private EditText course_title;
    private ProgressBar progressBar;
    private Context context;
    private ArrayAdapter adapter;
    ArrayList<String> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        context = this;
        progressBar = findViewById(R.id.loading);
        new TermList().execute("terms");
        addCourseBtn = findViewById(R.id.add_course);
        course_code = findViewById(R.id.code);
        course_title = findViewById(R.id.title);
        dropdown = findViewById(R.id.term);
        //String[] items = new String[]{"Fall 2019", "Spring 2018", "Summer 2018"};
        items = new ArrayList<>();
        adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        BottomAppBar appBar = findViewById(R.id.bottom_app_bar);
        setSupportActionBar(appBar);

        addCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AddCourse().execute("addCourse",course_code.getText().toString(), course_title.getText().toString(), "1");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_nav_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private class AddCourse extends AsyncTask<String, String, String> {
        private JSONObject jsonObject;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String[] args) {
            String message;
            String[] attributes = new String[]{"addCourse", "code", "title", "term_id"};
            RequestData requestData = new RequestData( context,"http://gamfruits.com/officient_api/functions.php", attributes, args);
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
                Toast.makeText(context, "Course added Successfully", Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(context, CoursesList.class);
//                startActivity(intent);
            }
            else if(result.equalsIgnoreCase("error")){
                Toast.makeText(context, "Unable to connect to the internet. Course not added", Toast.LENGTH_SHORT).show();
            }
            else if (result.equalsIgnoreCase("failed")){
                Toast.makeText(context, "Problem with App. Contact admin.", Toast.LENGTH_LONG).show();
            }
        }
    }

    private class TermList extends AsyncTask<String, String, String> {
        private JSONObject jsonObject;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String[] args) {
            String message;
            String[] attributes = new String[]{"terms"};
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
            if (result.equalsIgnoreCase("success")){
                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    JSONObject row;
                    for(int i=0;i<jsonArray.length();i++){
                        row = (JSONObject) jsonArray.get(i);
                        items.add(row.getString("name") +" ( From " +row.getString("start_date")+" to "+row.getString("end_date")+")");
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
                items.add("Term list empty");
                Toast.makeText(context, "term list empty", Toast.LENGTH_SHORT).show();
            }
            adapter.notifyDataSetChanged();


        }
    }
}
