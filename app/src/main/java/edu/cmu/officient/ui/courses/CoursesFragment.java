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

package edu.cmu.officient.ui.courses;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import edu.cmu.officient.R;
import edu.cmu.officient.ui.listener.AddAssignmentListener;
import edu.cmu.officient.ui.listener.AddCourseListener;
import edu.cmu.officient.ui.listener.QRCodeGeneratorListener;

public class CoursesFragment extends Fragment {

    private CoursesViewModel coursesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        coursesViewModel =
                ViewModelProviders.of(this).get(CoursesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        coursesViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        Button test = root.findViewById(R.id.test);
        test.setOnClickListener(new AddCourseListener((AppCompatActivity) getActivity()));

        Button addAss=root.findViewById(R.id.addAssignment);
        addAss.setOnClickListener( new AddAssignmentListener((AppCompatActivity) getActivity()));
        return root;
    }
}