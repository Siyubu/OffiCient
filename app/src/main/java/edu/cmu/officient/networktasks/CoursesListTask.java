/*
 *
 *  * @author Segla Boladji Vinny Trinite Adjibi
 *  * AndrewID : vadjibi
 *  * Program : MSIT
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

package edu.cmu.officient.networktasks;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import edu.cmu.officient.R;
import edu.cmu.officient.model.Course;
import edu.cmu.officient.ui.courses.CourseAdapter;
import edu.cmu.officient.util.ModelObjectBuilder;

public class CoursesListTask extends StandardRequestTask {
    private List<Course> courses = new ArrayList<>();
    private int parameterSize;
    private Spinner spinner;

    public CoursesListTask(AppCompatActivity activity, View root, View progress, int s, Spinner spinner, List<Course> courses) {
        super(activity, root, progress);
        this.spinner = spinner;
        this.courses = courses;
        parameterSize = s;
    }

    @Override
    protected String getOutput() {
        try {
            return getData().getString("message");
        } catch (JSONException e) {
            e.printStackTrace();
            return "error";
        }
    }

    @Override
    protected void processData(String s) {
        try {
            JSONArray jsonArray = getData().getJSONArray("data");
            JSONObject row;
            for(int i=0;i<jsonArray.length();i++){
                row = (JSONObject) jsonArray.get(i);
                courses.add(ModelObjectBuilder.buildCourse(row));
            }
            // Now we can set the adapter here
            ArrayAdapter adapter = new ArrayAdapter<>(getBaseActivity(), android.R.layout.simple_spinner_dropdown_item, courses);
            spinner.setAdapter(adapter);
            getRoot().findViewById(R.id.submit_action).setEnabled(true);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String[] getRequestParameters() {
        if (parameterSize > 1)
            return new String[]{"coursesList", "user_id"};
        else
            return new String[]{"coursesList"};
    }
}
