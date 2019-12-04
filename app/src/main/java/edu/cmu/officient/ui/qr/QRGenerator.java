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

public class QRGenerator extends AppCompatActivity {
   protected  int b_id;
   protected  int user_id;
   protected String object_type;
   protected String txt;

   protected String name;
   protected Date deadLine;
   protected Date available_till;
   protected Date published_date;



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

        qrImage=(ImageView) findViewById(R.id.imageView);
        start = (Button) findViewById(R.id.btnstart);
        final Button save = (Button) findViewById(R.id.btnsave);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                try {
                    Assignment assignment = new StaticStorage().getAssignment(4); // Get a default assignment
                    bitmap = QRImageGenerator.getQRCode(assignment);
                    //bitmap = qrgEncoder.encodeAsBitmap();
                    qrImage.setImageBitmap(bitmap);
                    save.setVisibility(View.VISIBLE);
                } catch (WriterException e) {
                    Log.v(TAG, e.toString());
                }
                saveQRCode();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SendQR_as_Email();
            }
        });
    }

    public void saveQRCode()
    {
        // Find the SD Card path
        File filepath = Environment.getExternalStorageDirectory();
        // Create a new folder in SD Card
        File dir = new File(filepath.getPath() + "/Mobilelife_Qr_Code/");
        dir.mkdirs();
        // Create a name for the saved image
        file = new File(dir, "Officient.png");
       String  path=file.getPath();
        // Show a toast message on successful save
        Toast.makeText(QRGenerator.this, "Image Saved to SD Card in "+path,
                Toast.LENGTH_SHORT).show();
        try {

           FileOutputStream output = new FileOutputStream(file);

            // Compress into png format image from 0% - 100%
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
            output.flush();
            output.close();
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ;
    }
    void SendQR_as_Email() {
        String emails = "solangeiyubu@gmail.com";
        String subject = "QR code";

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("image/png");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, emails);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        Uri uri = FileProvider.getUriForFile(this,this.getApplicationContext()
                        .getPackageName() + ".provider", file);
        emailIntent.putExtra(Intent.EXTRA_STREAM ,uri);
        emailIntent.setType("image/png");
        startActivity(emailIntent);
    }


}
