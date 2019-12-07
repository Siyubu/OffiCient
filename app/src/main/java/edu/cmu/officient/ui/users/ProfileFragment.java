/*
 *
 *  * @author Solange Iyubu
 *  * AndrewID : siyubu
 *  * Program : MSECE
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

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import edu.cmu.officient.R;
import edu.cmu.officient.logic.ApplicationManager;
import edu.cmu.officient.model.User;

public class ProfileFragment extends Fragment {

    private TextView header;
    private TextView name;
    private TextView email;
    private TextView altEmail;
    private TextView password;
    private Button update;
    private Button done;
    private Button logout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        profileViewModel =
//                ViewModelProviders.of(this).get(ProfileViewModel.class);
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        header = view.findViewById(R.id.header);
        name = view.findViewById(R.id.name);
        email = view.findViewById(R.id.email);
        altEmail = view.findViewById(R.id.altEmail);
        password = view.findViewById(R.id.password);
        update = view.findViewById(R.id.update);
        done = view.findViewById(R.id.done);

        final User user = ApplicationManager.getInstance().getLoggedInUser(getContext());
        header.setText("My Profile ("+ user.getAndrewId()+")");
        if(!user.getFullname().equalsIgnoreCase("")){
            name.setText(user.getFullname());
        }
        email.setText(user.getAndrewId()+"@andrew.cmu.edu");
        if(!user.getAltEmail().equalsIgnoreCase("")){
            altEmail.setText(user.getFullname());
        }

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setEnabled(true);
                email.setEnabled(true);
                altEmail.setEnabled(true);
                password.setEnabled(true);
                update.setEnabled(false);
                done.setEnabled(true);

            }
        });
        //final TextView textView = view.findViewById(R.id.text_notifications);
//        profileViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return view;
    }
}