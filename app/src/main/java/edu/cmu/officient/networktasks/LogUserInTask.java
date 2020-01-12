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

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONException;

import edu.cmu.officient.logic.ApplicationManager;
import edu.cmu.officient.ui.MainActivity;

public class LogUserInTask extends StandardRequestTask {
    public LogUserInTask(AppCompatActivity activity, View root, View progress) {
        super(activity, root, progress);
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
        if (s.equalsIgnoreCase("success")) {

            Toast.makeText(getBaseActivity(), "Login Successful", Toast.LENGTH_SHORT).show();
            // Log in to the app (i.e store data to local storage)
            try {
                ApplicationManager.getInstance(getBaseActivity())
                        .logUserIn(getData().getJSONObject("data"), getData().getBoolean("isFaculty"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Intent intent = new Intent(getBaseActivity(), MainActivity.class);
            getBaseActivity().startActivity(intent);
            getBaseActivity().finish();
        }
        else if (s.equalsIgnoreCase("no_account")){
            Toast.makeText(getBaseActivity(), "You have not registered", Toast.LENGTH_SHORT).show();
        }
        else if (s.equalsIgnoreCase("wrong_password")){
            try {
                System.out.println(getData().getString("data"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Toast.makeText(getBaseActivity(), "Wrong password", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected String[] getRequestParameters() {
        return new String[]{"login", "andrewId", "password"};
    }
}
