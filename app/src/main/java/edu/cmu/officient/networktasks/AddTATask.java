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

public class AddTATask extends StandardRequestTask {

    public AddTATask(AppCompatActivity activity, View root, View progressMarker) {
        super(activity, root, progressMarker);
    }

    @Override
    protected String getOutput() {
        System.out.println(getData());
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
            Toast.makeText(getRoot().getContext(), "Added successfully", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getRoot().getContext(), "An error occured while processing your request.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected String[] getRequestParameters() {
        return new String[]{"enrollTA", "course_id", "andrewId"};
    }
}
