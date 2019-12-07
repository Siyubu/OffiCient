/*
 *
 *  * @author Segla Boladji Vinny Trinite Adjibi
 *  * AndrewID : vadjibi
 *  * Program : MSIT
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

package edu.cmu.officient.ui.courses;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import edu.cmu.officient.DBCommunication.RequestData;
import edu.cmu.officient.R;
import edu.cmu.officient.model.Course;
import edu.cmu.officient.model.Term;
import edu.cmu.officient.util.DateConversion;

public class AddCourseFragment extends Fragment {
    private AppCompatActivity activity;
    private Spinner dropdown;
    private Button addCourseBtn;
    private EditText course_code;
    private EditText course_title;
    private ProgressBar progressBar;
    private ArrayAdapter adapter;
    private ArrayList<Term> items;
    private Term selectedTerm;
    public AddCourseFragment(AppCompatActivity activity)
    {
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_course, container, false);
        progressBar = view.findViewById(R.id.loading);
        new TermList().execute("terms");
        addCourseBtn = view.findViewById(R.id.add_course);
        course_code = view.findViewById(R.id.code);
        course_title = view.findViewById(R.id.title);
        dropdown = view.findViewById(R.id.term);
        items = new ArrayList<>();
        adapter = new ArrayAdapter<>(activity, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        addCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedTerm !=null) {
                    new AddCourse().execute("addCourse", course_code.getText().toString(), course_title.getText().toString(), selectedTerm.getId() + "");
                }
                else{
                    Toast.makeText(activity, "Please select a term for the office hours", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedTerm = (Term) parent.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedTerm = null;
            }
        });

        return view;
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
            RequestData requestData = new RequestData( activity,"http://gamfruits.com/officient_api/functions.php", attributes, args);
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
                Toast.makeText(activity, "Course added Successfully", Toast.LENGTH_LONG).show();
                Term term = items.get(dropdown.getSelectedItemPosition());
                try {
                    JSONObject object = jsonObject.getJSONObject("data");
                    int id = object.getInt("course_id");
                    Course course = new Course(id,  course_title.getText().toString(), course_code.getText().toString(), term, null, null, null);
                    activity.getSupportFragmentManager().beginTransaction().remove(AddCourseFragment.this).commit();
                    FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, new CourseDetailFragment(activity, course));
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                catch (JSONException e) {/* */}

            }
            else if(result.equalsIgnoreCase("error")){
                Toast.makeText(activity, "Unable to connect to the internet. Course not added", Toast.LENGTH_SHORT).show();
            }
            else if (result.equalsIgnoreCase("failed")){
                Toast.makeText(activity, "Problem with App. Contact admin.", Toast.LENGTH_LONG).show();
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
            RequestData requestData = new RequestData( activity,"http://gamfruits.com/officient_api/functions.php", attributes, args);
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
                Toast.makeText(activity, "Term List Retrieved", Toast.LENGTH_SHORT).show();
            }
            else if(result.equalsIgnoreCase("error")){
                Toast.makeText(activity, "Unable to connect to the internet. The term list won't be updated", Toast.LENGTH_SHORT).show();
            }
            else if (result.equalsIgnoreCase("no_data")){
                //items.add("Term list empty");
                Toast.makeText(activity, "term list empty", Toast.LENGTH_SHORT).show();
            }
            adapter.notifyDataSetChanged();

        }
    }
}
