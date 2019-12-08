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
 *  * @author Wuyeh Jobe
 *  * AndrewID : jwuyeh
 *  * Program : MSIT
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

package edu.cmu.officient.ui.users;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.cmu.officient.DBCommunication.RequestData;
import edu.cmu.officient.R;
import edu.cmu.officient.logic.ApplicationManager;
import edu.cmu.officient.model.User;

public class ProfileFragment extends Fragment {
    private AppCompatActivity activity;
    private EditText name, altEmail, phoneNumber, password;
    private Button update, done;
    User user;
    public ProfileFragment(){

    }

    public ProfileFragment(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        user = ApplicationManager.getInstance(activity).getLoggedInUser();
        final EditText andrewId = root.findViewById(R.id.field_andrewId);
        name = root.findViewById(R.id.field_name);
        altEmail = root.findViewById(R.id.field_alt_email);
        phoneNumber = root.findViewById(R.id.field_phone_number);
        password = root.findViewById(R.id.field_password);
        update = root.findViewById(R.id.update);
        done = root.findViewById(R.id.done);

        andrewId.setText(user.getAndrewId());
        name.setText(user.getFullname());
        altEmail.setText(user.getAltEmail());
        phoneNumber.setText(user.getPhoneNumber());
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableFields();
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update.setEnabled(true);
                new EditProfile().execute("update_profile", andrewId.getText().toString(), name.getText().toString(), altEmail.getText().toString(), phoneNumber.getText().toString(),password.getText().toString());
                disableFields();
            }
        });

        return root;
    }

    private void enableFields(){
        name.setEnabled(true);
        altEmail.setEnabled(true);
        altEmail.setEnabled(true);
        phoneNumber.setEnabled(true);
        password.setEnabled(true);
        password.setHint("Leave it blank you don't wish to change");
        done.setVisibility(View.VISIBLE);
        update.setEnabled(false);
    }
    private  void disableFields(){
        name.setEnabled(false);
        altEmail.setEnabled(false);
        altEmail.setEnabled(false);
        phoneNumber.setEnabled(false);
        password.setEnabled(true);
        done.setVisibility(View.INVISIBLE);
        update.setEnabled(true);
    }

    private class EditProfile extends AsyncTask<String, String, String> {
        private JSONObject jsonObject;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            update.setText("updating...");
            done.setVisibility(View.GONE);
        }

        @Override
        protected String doInBackground(String[] args) {
            String message;
            String[] attributes = new String[]{"update_profile", "andrewId", "name", "altEmail", "phoneNumber","password"};
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
            update.setText("Edit");
            update.setEnabled(true);
            if (result.equalsIgnoreCase("success")){
                ApplicationManager.getInstance()
                        .logUserIn(activity,name.getText().toString(), altEmail.getText().toString(),phoneNumber.getText().toString());
                Toast.makeText(activity, "Profile Updated", Toast.LENGTH_SHORT).show();
            }
            else if(result.equalsIgnoreCase("error")){
                Toast.makeText(activity, "Unable to connect to the internet. The term list won't be updated", Toast.LENGTH_SHORT).show();
            }
            else if (result.equalsIgnoreCase("failed")){
                //items.add("Term list empty");
                Toast.makeText(activity, "There was a problem", Toast.LENGTH_SHORT).show();
            }

        }
    }
}