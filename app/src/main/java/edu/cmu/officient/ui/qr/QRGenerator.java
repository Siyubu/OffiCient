/*
 *
 *  * @author Solange Iyubu
 *  * AndrewID : siyubu
 *  * Program : ECE
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

package edu.cmu.officient.ui.qr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.WriterException;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import androidmads.library.qrgenearator.QRGEncoder;
import edu.cmu.officient.R;
import edu.cmu.officient.api.qrcode.QRImageGenerator;
import edu.cmu.officient.model.Assignment;
import edu.cmu.officient.storage.StaticStorage;
import edu.cmu.officient.ui.listener.QRCodeSendByEmailListener;

public class QRGenerator extends AppCompatActivity {
   protected String name;

   protected String TAG = "GenerateQRCode";
    private ImageView qrImage;
    private  Button start;
    private Bitmap bitmap;
    private QRGEncoder qrgEncoder;
    private File file;

    public QRGenerator()
    {}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrgeneration);

        qrImage= findViewById(R.id.imageView);
        start = findViewById(R.id.btnstart);
        final Button save = (Button) findViewById(R.id.btnsave);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                try {
                    Assignment assignment = new StaticStorage().getAssignment(4); // Get a default assignment
                    bitmap = new QRImageGenerator(QRGenerator.this).getQRCode(assignment);
                    //bitmap = qrgEncoder.encodeAsBitmap();
                    qrImage.setImageBitmap(bitmap);
                    save.setOnClickListener(new QRCodeSendByEmailListener(QRGenerator.this, bitmap));
                    save.setVisibility(View.VISIBLE);
                } catch (WriterException e) {
                    Log.v(TAG, e.toString());
                }
            }
        });
    }
}
