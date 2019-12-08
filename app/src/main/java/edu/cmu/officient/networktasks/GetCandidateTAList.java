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

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import edu.cmu.officient.model.User;
import edu.cmu.officient.ui.courses.AddTADialogFragment;
import edu.cmu.officient.util.ModelObjectBuilder;

public class GetCandidateTAList extends StandardRequestTask {
    private int courseId;

    public GetCandidateTAList(AppCompatActivity activity, View root, View progressMarker, int course_id) {
        super(activity, root, progressMarker);
        courseId = course_id;
    }

    @Override
    protected String getOutput() {
        try {
            return getData().getString("message");
        }
        catch (JSONException e) {
            e.printStackTrace();
            return "error";
        }
    }

    @Override
    protected void processData(String s) {
        if (s.equalsIgnoreCase("success")) {
            try {
                JSONArray array = getData().getJSONArray("data");
                String [] candidates = new String[array.length()];
                for (int i=0; i<array.length(); ++i) {
                    JSONObject data = (JSONObject) array.get(i);
                    //candidates[i] = ModelObjectBuilder.buildUser(data);
                    System.out.println(data);
                    candidates[i] = data.getString("andrewId");
                }
                AddTADialogFragment fragment = new AddTADialogFragment(candidates, courseId);
                fragment.show(getBaseActivity().getSupportFragmentManager(), "Pick a TA");
            }
            catch (JSONException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    protected String[] getRequestParameters() {
        return new String[]{"allStudents", "course_id"};
    }
}
