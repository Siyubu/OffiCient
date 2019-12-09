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

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import edu.cmu.officient.R;
import edu.cmu.officient.logic.ApplicationManager;
import edu.cmu.officient.model.User;
import edu.cmu.officient.networktasks.RequestTaskFactory;
import edu.cmu.officient.networktasks.StandardRequestTask;

public class ProfileFragment extends Fragment {
    private AppCompatActivity activity;
    private EditText name, altEmail, phoneNumber, password;
    private Button update, done, logout;
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
        final View root = inflater.inflate(R.layout.fragment_profile, container, false);
        User user = ApplicationManager.getInstance(activity).getLoggedInUser();
        final EditText andrewId = root.findViewById(R.id.field_andrewId);
        name = root.findViewById(R.id.field_name);
        altEmail = root.findViewById(R.id.field_alt_email);
        phoneNumber = root.findViewById(R.id.field_phone_number);
        password = root.findViewById(R.id.field_password);
        update = root.findViewById(R.id.update);
        done = root.findViewById(R.id.done);
        logout = root.findViewById(R.id.logout);
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
                StandardRequestTask task = RequestTaskFactory.getTask(done, root, activity, null, "update_profile", andrewId.getText().toString(), name.getText().toString(), altEmail.getText().toString(), phoneNumber.getText().toString(),password.getText().toString());
                if (task != null)
                    task.execute("update_profile", andrewId.getText().toString(), name.getText().toString(), altEmail.getText().toString(), phoneNumber.getText().toString(),password.getText().toString());
                disableFields();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ApplicationManager.getInstance(activity).logUserOut()) {
                    Intent intent = new Intent(activity, LoginActivity.class);
                    startActivity(intent);
                    activity.finish();
                }
            }
        });

        return root;
    }

    private void enableFields(){
        name.setEnabled(true);
        altEmail.setEnabled(true);
        phoneNumber.setEnabled(true);
        password.setEnabled(true);
        password.setHint("Leave it blank you don't wish to change");
        name.setHint("update name");
        altEmail.setHint("update alternative email");
        phoneNumber.setHint("update phone number");
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
}