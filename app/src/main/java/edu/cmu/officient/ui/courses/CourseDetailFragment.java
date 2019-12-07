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

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.WriterException;

import edu.cmu.officient.R;
import edu.cmu.officient.api.qrcode.QRImageGenerator;
import edu.cmu.officient.model.Course;

public class CourseDetailFragment extends Fragment {
    private Course course;
    private AppCompatActivity activity;

    public CourseDetailFragment(AppCompatActivity activity, Course course) {
        this.activity = activity;
        this.course = course;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity.getSupportActionBar().setTitle(R.string.course_details_label);
        // Inflate the layout for this fragment
        TextView title, code, term;
        ImageView qrCode;
        Button modify, finish;

        View view = inflater.inflate(R.layout.fragment_course_detail, container, false);
        title = view.findViewById(R.id.course_title);
        code = view.findViewById(R.id.course_code);
        term = view.findViewById(R.id.course_term);
        modify = view.findViewById(R.id.modify);
        finish = view.findViewById(R.id.finish);

        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Here we should start the fragment to set details of a course
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.getSupportFragmentManager().beginTransaction().remove(CourseDetailFragment.this).commit();
                FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, new CoursesFragment(activity));
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        title.setText(getString(R.string.object_title, course.getTitle()));
        code.setText(getString(R.string.object_code, course.getCode()));
        term.setText(getString(R.string.term_title, course.getTerm()));

        return view;
    }
}
