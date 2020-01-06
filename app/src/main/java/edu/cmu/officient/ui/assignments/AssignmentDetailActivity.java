/*
 *
 *  * @author Segla Boladji Vinny Trinite Adjibi
 *  * AndrewID : vadjibi
 *  * Program : MSIT
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

package edu.cmu.officient.ui.assignments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.WriterException;

import edu.cmu.officient.R;
import edu.cmu.officient.api.qrcode.QRImageGenerator;
import edu.cmu.officient.model.Assignment;
import edu.cmu.officient.model.Course;
import edu.cmu.officient.ui.listener.QRCodeSendByEmailListener;

public class AssignmentDetailActivity extends AppCompatActivity {

    private Assignment assignment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_detail);

        Intent intent = getIntent();
        assignment = (Assignment) intent.getSerializableExtra("assignment");

        getSupportActionBar().setTitle(R.string.assignment_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView imageView = findViewById(R.id.imageView);
        TextView ass_name = findViewById(R.id.assignment_name), due_date = findViewById(R.id.due_date);
        Button send_email_btn = findViewById(R.id.send_code);
        Bitmap assignQR;

        try {
            assignQR = new QRImageGenerator(this).getQRCode(assignment);
            imageView.setImageBitmap(assignQR);
            ass_name.setText(getString(R.string.assignment_title, assignment.getTitle()));
            due_date.setText(getString(R.string.object_deadline, assignment.getDeadline().toString()));
        }
        catch (WriterException e) {
            e.printStackTrace();
            assignQR =  null;
        }
        send_email_btn.setOnClickListener(new QRCodeSendByEmailListener(this,assignQR));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("assignment", assignment);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        assignment = (Assignment) savedInstanceState.getSerializable("assignment");
    }
}
