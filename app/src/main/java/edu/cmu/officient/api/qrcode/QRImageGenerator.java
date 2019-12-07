/*
 *
 *  * @author Solange Iyubu
 *  * AndrewID : siyubu
 *  * Program : ECE
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

package edu.cmu.officient.api.qrcode;

import android.content.Context;
import android.graphics.Bitmap;

import com.google.zxing.WriterException;

import edu.cmu.officient.logic.ApplicationManager;
import edu.cmu.officient.model.Assignment;
import edu.cmu.officient.model.Course;
import edu.cmu.officient.model.OfficeHours;
import edu.cmu.officient.model.Scannable;

public class QRImageGenerator {
    private Context context;

    public QRImageGenerator(Context context) {
        this.context = context;
    }

    public Bitmap getQRCode(Scannable scannable) throws WriterException
    {
        QRBuilder builder;
        if (scannable instanceof Assignment)
        {
            // Here assignment
            Assignment assignment = (Assignment) scannable;
            builder = new AssignmentQRBuilder();
            builder.setId(assignment.getId());
            builder.setName(assignment.getTitle());
            builder.setType(ObjectTypeFactory.getObjectType(scannable));
            builder.setDeadline(assignment.getDeadline());
            builder.setAvailableTill(assignment.getAvailableTill());
            builder.setPublished_time(assignment.getPublishedOn());

            return builder.build().encodeAsBitmap();
        }
        else if (scannable instanceof Course) {
            // Here Course
            Course course = (Course) scannable;
            builder = new CourseQRBuilder();

            builder.setId(course.getId());
            builder.setOwnerId(ApplicationManager.getInstance().getLoggedInUser(context).getId()); // Assuming it is generated only by instructors
            builder.setName(course.getTitle());

            return builder.build().encodeAsBitmap();
        }
        else if (scannable instanceof OfficeHours) {
            OfficeHours officeHours = (OfficeHours) scannable;
            builder = new OfficeHoursQRBuilder();

            builder.setId(officeHours.getId());
            builder.setType(ObjectTypeFactory.getObjectType(scannable));
            builder.setStartAt(officeHours.getStartAt());
            builder.setEndAt(officeHours.getEndAt());
            builder.setDayOfTheWeek(officeHours.getDay());
            builder.setOwnerId(ApplicationManager.getInstance().getLoggedInUser(context).getId()); // QR Code is only seen by creator

            return builder.build().encodeAsBitmap();
        }
        return null; // Not handled now
    }
}
