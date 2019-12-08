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

public class EnrollUserTask extends StandardRequestTask {
    private final int [] hide = new int[]{R.id.enroll_in}, show = new int[]{R.id.view_statistics};

    public EnrollUserTask(AppCompatActivity activity, View root, View progressMarker) {
        super(activity, root, progressMarker);
    }

    @Override
    protected String getOutput() {
        try {
            System.out.println(getData());
            return getData().getString("message");
        }
        catch (JSONException e) {
            return "error";
        }
    }

    @Override
    protected void processData(String s) {
        if (s.equalsIgnoreCase("failed")) {
            Toast.makeText(getBaseActivity(), "Failed to add you to the course.", Toast.LENGTH_SHORT).show();
        }
        else {
            for (int id : hide)
                getRoot().findViewById(id).setVisibility(View.GONE);
            for (int id : show)
                getRoot().findViewById(id).setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected String[] getRequestParameters() {
        return new String[]{"enrollStudent", "andrewId", "course_id"};
    }
}
