/*
 *
 *  * @author Segla Boladji Vinny Trinite Adjibi
 *  * AndrewID : vadjibi
 *  * Program : MSIT
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

package edu.cmu.officient.ui.office_hours;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.WriterException;

import java.text.DateFormatSymbols;
import java.util.Locale;

import edu.cmu.officient.R;
import edu.cmu.officient.api.qrcode.QRImageGenerator;
import edu.cmu.officient.model.OfficeHours;
import edu.cmu.officient.ui.listener.QRCodeSendByEmailListener;

public class OfficeHourDetailActivity extends AppCompatActivity {
    private OfficeHours officeHour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_office_hour_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.details);

        Intent intent = getIntent();
        officeHour = (OfficeHours) intent.getSerializableExtra("office_hour");

        ImageView imageView = findViewById(R.id.imageView);
        TextView instructor = findViewById(R.id.instructor_name), desc = findViewById(R.id.description), details = findViewById(R.id.time_and_venue);
        Button send_email_btn = findViewById(R.id.send_code);
        Bitmap assignQR;

        try {
            assignQR = new QRImageGenerator(this).getQRCode(officeHour);
            imageView.setImageBitmap(assignQR);
            instructor.setText(officeHour.getOwnerId());
            desc.setText(officeHour.getDescription());
            details.setText(getString(R.string.time_and_venue, getString(R.string.oh_day_hours,
                    DateFormatSymbols.getInstance(Locale.getDefault()).getWeekdays()[officeHour.getDay()], officeHour.getStartAt(), officeHour.getEndAt()), officeHour.getVenue()));
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
        outState.putSerializable("office_hour", officeHour);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        officeHour = (OfficeHours) savedInstanceState.getSerializable("office_hour");
    }
}
