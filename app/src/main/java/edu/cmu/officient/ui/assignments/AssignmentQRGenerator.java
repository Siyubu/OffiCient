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
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.WriterException;
import edu.cmu.officient.api.qrcode.QRImageGenerator;
import edu.cmu.officient.model.Assignment;
import edu.cmu.officient.ui.qr.QRGenerator;

public class AssignmentQRGenerator {
    private Assignment assignment;
    private QRGenerator assignQRGenerator;
    private AddAssignmentFragment addedAssignment;
    private String addedAssignmentTitle;

    public AssignmentQRGenerator()
    {

    }

    public void getAssignmentQRGenerator(final EditText editText, AppCompatActivity activity) throws WriterException {
        Assignment assignment=new Assignment(); // loop throught database and check if the assignment exist then create QR Image
        Bitmap assignQRGenerator;
        if(assignment.getTitle().equalsIgnoreCase(editText.getText().toString()))
        {
            assignQRGenerator=new QRImageGenerator(activity).getQRCode(assignment);

        }

    }
}
