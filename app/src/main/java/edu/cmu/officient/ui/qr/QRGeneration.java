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

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import edu.cmu.officient.R;

public class QRGeneration extends AppCompatActivity {
   public  int b_id;
   public  int user_id;
   public String object_type;
   private GetQRdata realdata;
   private String txt;

    private String TAG = "GenerateQRCode";
    EditText edtValue;
    private ImageView qrImage;
    private  Button start;
   // private String savePath = Environment.getExternalStorageDirectory().getPath() + "/QRCode/";
    private Bitmap bitmap;
    private QRGEncoder qrgEncoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrgeneration);

        qrImage=(ImageView) findViewById(R.id.imageView);
        edtValue = (EditText) findViewById(R.id.txt);
        start = (Button) findViewById(R.id.btnstart);
        final Button save = (Button) findViewById(R.id.btnsave);
        realdata=new GetQRdata();
        txt=realdata.qrData();

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txt.length() > 0) {

                    //calculating bitmap dimension
                    WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
                    Display display = manager.getDefaultDisplay();
                    Point point = new Point();
                    display.getSize(point);
                    int width = point.x;
                    int height = point.y;
                    int smallerDimension = width < height ? width : height;
                    smallerDimension = smallerDimension * 3 / 4;

                    qrgEncoder = new QRGEncoder(txt, null, QRGContents.Type.TEXT, smallerDimension);
                    try {
                        bitmap = qrgEncoder.encodeAsBitmap();
                        qrImage.setImageBitmap(bitmap);
                        save.setVisibility(View.VISIBLE);
                    } catch (WriterException e) {
                        Log.v(TAG, e.toString());
                    }
                } else {
                    edtValue.setError("Enter some text");
                }
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openQr_scaner_Activity();

            }
        });
    }
    void openQr_scaner_Activity()
    {
        Intent intent=new Intent(this,QRCode_scanner.class);
        startActivity(intent);

    }



}
