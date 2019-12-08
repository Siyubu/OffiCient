/*
 *
 *  * @author Segla Boladji Vinny Trinite Adjibi
 *  * AndrewID : vadjibi
 *  * Program : MSIT
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

package edu.cmu.officient.networktaks;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import edu.cmu.officient.model.Course;
import edu.cmu.officient.ui.courses.CourseAdapter;
import edu.cmu.officient.util.ModelObjectBuilder;

public class CoursesListQueryTask extends StandardRequestTask {
    private int parameterSize;
    private RecyclerView recyclerView;

    public CoursesListQueryTask(AppCompatActivity activity, View root, View progress, int s, RecyclerView recycler) {
        super(activity, root, progress);
        this.recyclerView = recycler;
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
            List<Course> courses = new ArrayList<>();
            JSONArray jsonArray = getData().getJSONArray("data");
            JSONObject row;
            for(int i=0;i<jsonArray.length();i++){
                row = (JSONObject) jsonArray.get(i);
                courses.add(ModelObjectBuilder.buildCourse(row));
            }
            // Now we can set the adapter here
            CourseAdapter adapter = new CourseAdapter(getBaseActivity(), courses);
            recyclerView.setAdapter(adapter);
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
