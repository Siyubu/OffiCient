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
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import edu.cmu.officient.DBCommunication.RequestData;
import edu.cmu.officient.R;
import edu.cmu.officient.ui.util.NavigationInitializer;
import edu.cmu.officient.model.Term;
import edu.cmu.officient.util.DateConversion;

public class AddCourseActivity extends AppCompatActivity {

    private Spinner dropdown;
    private Button addCourseBtn;
    private EditText course_code;
    private EditText course_title;
    private ProgressBar progressBar;
    private Context context;
    private ArrayAdapter adapter;
    ArrayList<Term> items;
    Term selectedTerm;
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
        items = new ArrayList<>();
        adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        /*BottomNavigationView navView = findViewById(R.id.nav_view);
        NavigationInitializer.setUpBottomNavigationBar(navView);*/

        addCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedTerm !=null) {
                    new AddCourse().execute("addCourse", course_code.getText().toString(), course_title.getText().toString(), selectedTerm.getId() + "");
                }
                else{
                    Toast.makeText(context, "Please select a term for the office hours", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedTerm = (Term) parent.getSelectedItem();
                Toast.makeText(context, selectedTerm.getId()+"", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedTerm = null;
            }
        });

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
            DateConversion dateConversion = new DateConversion();
            if (result.equalsIgnoreCase("success")){
                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    JSONObject row;
                    for(int i=0;i<jsonArray.length();i++){
                        row = (JSONObject) jsonArray.get(i);
                        items.add(new Term(row.getInt("id"), row.getString("name"),
                                dateConversion.stringToDate(row.getString("start_date")),
                                dateConversion.stringToDate(row.getString("end_date"))));
                        //items.add(row.getString("name") +" ( From " +row.getString("start_date")+" to "+row.getString("end_date")+")");
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
