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
 *  * @author Segla Boladji Vinny Trinite Adjibi
 *  * AndrewID : vadjibi
 *  * Program : MSIT
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

package edu.cmu.officient.ui.users;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import edu.cmu.officient.R;
import edu.cmu.officient.logic.ApplicationManager;
import edu.cmu.officient.model.User;

public class ProfileFragment extends Fragment {
    private AppCompatActivity activity;
    private EditText name, altEmail, phoneNumber, password;

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
        User user = ApplicationManager.getInstance(activity).getLoggedInUser();
        EditText andrewId = root.findViewById(R.id.field_andrewId);
        name = root.findViewById(R.id.field_name);
        altEmail = root.findViewById(R.id.field_alt_email);
        phoneNumber = root.findViewById(R.id.field_phone_number);
        password = root.findViewById(R.id.field_password);


        andrewId.setText(user.getAndrewId());
        name.setText(user.getFullname());
        altEmail.setText(user.getAltEmail());
        phoneNumber.setText(user.getPhoneNumber());

        // The buttons


        return root;
    }
}