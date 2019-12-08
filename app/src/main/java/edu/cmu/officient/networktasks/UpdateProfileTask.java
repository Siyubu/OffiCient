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

import edu.cmu.officient.logic.ApplicationManager;

public class UpdateProfileTask extends StandardRequestTask {
    private String [] fields;
    public UpdateProfileTask(AppCompatActivity activity, View root, View progressMarker, String ... args) {
        super(activity, root, progressMarker);
        fields = new String[args.length - 1];
        for (int i=1; i<args.length; ++i) {
            fields[i-1] = args[i];
        }
    }

    @Override
    protected String getOutput() {
        try {
            return getData().getString("message");
        }
        catch (JSONException e) {
            e.printStackTrace();
            return  "error";
        }
    }

    @Override
    protected void processData(String s) {
        // We assume that the user has sent the data appropriately. Not good coding but we stick to it for now for the sake of code clarity
        ApplicationManager.getInstance()
                .logUserIn(getBaseActivity(), fields[0], fields[1], fields[2]);
        Toast.makeText(getBaseActivity(), "Profile Updated", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected String[] getRequestParameters() {
        return new String[]{"update_profile", "andrewId", "name", "altEmail", "phoneNumber","password"};
    }
}
