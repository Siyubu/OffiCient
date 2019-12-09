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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

import edu.cmu.officient.R;
import edu.cmu.officient.ui.courses.CourseDetailFragment;
import edu.cmu.officient.ui.courses.CoursesFragment;

public class AddAssignmentTask extends StandardRequestTask {
    public AddAssignmentTask(AppCompatActivity activity, View root, View progressMarker) {
        super(activity, root, progressMarker);
    }

    @Override
    protected String getOutput() {
        try {
            return getData().getString("message");
        }
        catch (JSONException e) {
            return  "error";
        }
    }

    @Override
    protected void processData(String s) {
        if (s.equalsIgnoreCase("success")){
            Toast.makeText(getBaseActivity(), R.string.successfully_added, Toast.LENGTH_LONG).show();
            // Navigate to the CourseDetail Fragment
            /*getBaseActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CoursesFragment()).commit();*/
        }
    }

    @Override
    protected String[] getRequestParameters() {
        return new String[]{"addAssignment", "title", "deadline", "published_on", "availability","expected_time","course_id"};
    }
}
