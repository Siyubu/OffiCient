/*
 *
 *  * @author Solange iyubu
 *  * AndrewID : siyubu
 *  * Program : MSECE
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

package edu.cmu.officient.ui.assignments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.zxing.WriterException;

import edu.cmu.officient.R;
import edu.cmu.officient.api.qrcode.QRImageGenerator;
import edu.cmu.officient.model.Assignment;
import edu.cmu.officient.ui.listener.QRCodeGeneratorListener;
import edu.cmu.officient.ui.listener.QRCodeSendByEmailListener;
import edu.cmu.officient.ui.qr.QRGenerator;

public class AssignmentQRGenerator extends Fragment {
    private Assignment assignment;
    private AppCompatActivity activity;
    private ImageView imageView;
    private TextView ass_name, due_date;
    private Button send_email_btn;



    public AssignmentQRGenerator(AppCompatActivity activity,Assignment assignment) {
        this.activity = activity;
        this.assignment = assignment;
    }
    public AssignmentQRGenerator()
    {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity.getSupportActionBar().setTitle(R.string.assignment_details);
        final View root = inflater.inflate(R.layout.frament_assignment_details, container, false);
        imageView = root.findViewById(R.id.imageView);
        ass_name = root.findViewById(R.id.assignment_name);
        due_date =root.findViewById(R.id.due_date);
        send_email_btn = root.findViewById(R.id.send_code);
        Bitmap assignQR;
        try {
            assignQR = new QRImageGenerator(activity).getQRCode(assignment);
            imageView.setImageBitmap(assignQR);
            ass_name.setText("Assignment: "+assignment.getTitle());
            due_date.setText("Deadline: "+assignment.getDeadline().toString());
        } catch (WriterException e) {
            e.printStackTrace();
            assignQR =  null;
        }
        send_email_btn.setOnClickListener(new QRCodeSendByEmailListener(activity,assignQR));

        return root;
    }
}
