/*
 *
 *  * @author Segla Boladji Vinny Trinite Adjibi
 *  * AndrewID : vadjibi
 *  * Program : MSIT
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

package edu.cmu.officient.ui.listener;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

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
        // Inflate the layout for this fragment
        TextView title, code, term;
        ImageView qrCode;
        Button send, finish;

        View view = inflater.inflate(R.layout.fragment_course_detail, container, false);
        title = view.findViewById(R.id.course_title);
        code = view.findViewById(R.id.course_code);
        term = view.findViewById(R.id.course_term);
        send = view.findViewById(R.id.send_code);
        finish = view.findViewById(R.id.finish);

        title.setText(getString(R.string.object_title, course.getTitle()));
        code.setText(getString(R.string.object_code, course.getCode()));
        term.setText(getString(R.string.term_title, course.getTerm()));


        try {
            Bitmap bitmap = new QRImageGenerator(activity).getQRCode(course);
            //qrCode.setImageBitmap(bitmap);
            finish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(activity, "Finishing the adding", Toast.LENGTH_SHORT).show();
                }
            });
            send.setOnClickListener(new QRCodeSendByEmailListener(activity, bitmap));
        }
        catch (WriterException e) {
            /* */
        }
        activity.getSupportActionBar().setTitle(R.string.course_details_label);
        return view;
    }
}
