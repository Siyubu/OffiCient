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

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

import edu.cmu.officient.ui.users.SuccessfulRegistration;

public class RegisterUserTask extends StandardRequestTask {
    public RegisterUserTask(AppCompatActivity activity, View root, View progressMarker) {
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
        if(s.equalsIgnoreCase("already_registered")){
            Toast.makeText(getBaseActivity(), "User is already registered", Toast.LENGTH_SHORT).show();
        }
        else{
            Intent intent = new Intent(getBaseActivity(), SuccessfulRegistration.class);
            getBaseActivity().startActivity(intent);
        }

    }

    @Override
    protected String[] getRequestParameters() {
        return new String[]{"signup", "andrewId", "password"};
    }
}
