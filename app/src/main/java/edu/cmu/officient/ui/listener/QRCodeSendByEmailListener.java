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

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.view.View;

import androidx.core.content.FileProvider;

import com.google.zxing.WriterException;

import java.io.File;
import java.util.UUID;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGSaver;
import edu.cmu.officient.logic.ApplicationManager;
import edu.cmu.officient.model.User;

public class QRCodeSendByEmailListener implements View.OnClickListener  {
    private Context context;
    private Bitmap bitmap;

    public QRCodeSendByEmailListener(Context context, Bitmap bitmap) {
        this.context = context;
        this.bitmap = bitmap;
    }

    @Override
    public void onClick(View view){
        try {
            File filepath = context.getExternalFilesDir("codes/");
            String directory = filepath.getAbsolutePath();
            String filename = UUID.randomUUID().toString();
            boolean result = QRGSaver.save(directory+"/", filename, bitmap, QRGContents.ImageType.IMAGE_PNG);
            if (result) {
                User currentUser = ApplicationManager.getInstance().getLoggedInUser(context);
                String [] emails = new String[] {currentUser.getEmail(), currentUser.getAltEmail()} ;
                String subject = "Your QR Code";
                File file = new File(directory + "/" + filename + ".png");
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.putExtra(Intent.EXTRA_EMAIL, emails);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
                Log.e("FILE", file.getAbsolutePath());
                Uri uri = FileProvider.getUriForFile(context,context.getApplicationContext().getPackageName() + ".provider", file);
                emailIntent.putExtra(Intent.EXTRA_STREAM ,uri);
                emailIntent.setType("image/png");
                context.startActivity(emailIntent);
            }
        }
        catch (WriterException | NullPointerException e) {
            /* */
        }
    }
}
