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

package edu.cmu.officient.ui.courses;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.cmu.officient.DBCommunication.RequestData;
import edu.cmu.officient.R;
import edu.cmu.officient.logic.ApplicationManager;
import edu.cmu.officient.model.Course;
import edu.cmu.officient.model.Term;
import edu.cmu.officient.model.User;
import edu.cmu.officient.ui.customviews.AdvancedRecyclerView;
import edu.cmu.officient.util.ModelObjectBuilder;

public class CoursesFragment extends Fragment {

    private ProgressBar progressBar;
    private AppCompatActivity activity;
    private List<Course> courses = new ArrayList<>();
    private RecyclerView recyclerView;


    public CoursesFragment(){
        activity = (AppCompatActivity) getActivity();
    }

    public CoursesFragment (AppCompatActivity activity) {
        this.activity = activity;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_courses, container, false);
        User user = ApplicationManager.getInstance(activity).getLoggedInUser();

        progressBar = root.findViewById(R.id.progress_bar);
        recyclerView = root.findViewById(R.id.courses_list);

        if (courses.size() == 0 ) {
            if (user.isFaculty())
                new CourseList().execute("coursesList", "" + user.getId()); // can add all the
            else
                new CourseList().execute("coursesList");
        }
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager llm = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(llm);
        return root;
    }

    private class CourseList extends AsyncTask<String, String, String> {
        private JSONObject jsonObject;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... args) {
            String message;
            String[] attributes;
            if (args.length > 1)
                attributes = new String[]{"coursesList", "user_id"};
            else
                attributes = new String[]{"coursesList"};
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
            if (result.equalsIgnoreCase("success")){
                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    JSONObject row;
                    for(int i=0;i<jsonArray.length();i++){
                        row = (JSONObject) jsonArray.get(i);
                        courses.add(ModelObjectBuilder.buildCourse(row));
                    }
                    // Now we can set the adapter here
                    CourseAdapter adapter = new CourseAdapter(activity, courses);
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else if(result.equalsIgnoreCase("error")){
                Toast.makeText(activity, R.string.network_unavailable, Toast.LENGTH_SHORT).show();
            }
            else if (result.equalsIgnoreCase("no_data")){
                Toast.makeText(activity, R.string.data_empty, Toast.LENGTH_SHORT).show();
            }
        }
    }
}