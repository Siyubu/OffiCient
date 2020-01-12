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

import android.util.Log;

import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import edu.cmu.officient.model.*;
import edu.cmu.officient.storage.*;
import edu.cmu.officient.util.Time;

public class ScannedCodeFactory {
    public static ScannedQRCode loadCode(String output) {
        Properties properties = new Properties();
        try {
            properties.load(new StringReader(output));
            // Collect the type and process it
            ObjectType type = ObjectType.fromString(properties.getProperty("TYPE"));
            // Get the common data as well
            int id=0, courseId=0;
            try {
                id = Integer.parseInt(properties.getProperty("ID"));
            }
            catch (NumberFormatException e) {
                /* */
            }
            String stringID = "", name = "";
            try {
                id = Integer.parseInt(properties.getProperty("ID"));
                courseId = Integer.parseInt(properties.getProperty("COURSE_ID"));
                name = properties.getProperty("NAME");
            }
            catch (NumberFormatException e) {
                stringID = properties.getProperty("ID");
            }

            SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
            switch (type){
                case ASSIGNMENT:
                    // Get additional data like Course name
                    Date deadline = formatter.parse(properties.getProperty("DEADLINE")),
                            availableTill = formatter.parse(properties.getProperty("AVAILABLE_TILL")),
                            publishedOn = formatter.parse(properties.getProperty("PUBLISHED_DATE"));
                    Assignment assignment = new Assignment();
                    assignment.setId(id);
                    assignment.setTitle(name);
                    assignment.setAvailableTill(availableTill);
                    assignment.setDeadline(deadline);
                    assignment.setPublishedOn(publishedOn);
                    assignment.setCourseId(courseId);
                    return new ScannedQRCode(assignment);
                case OFFICE_HOURS:
                    Time startAt= Time.parse(properties.getProperty("START_AT")), endAt = Time.parse(properties.getProperty("END_AT"));
                    int day = Integer.parseInt(properties.getProperty("DAY")); //, instructorId = Integer.parseInt(properties.getProperty("OWNER_ID"));
                    String andrew = properties.getProperty("ANDREW_ID");
                    OfficeHours officeHours = new OfficeHours();
                    officeHours.setId(stringID);
                    officeHours.setStartAt(startAt);
                    officeHours.setEndAt(endAt);
                    officeHours.setDay(day);
                    Log.e("TEST", properties.toString());
                    officeHours.setCourseId(properties.getProperty("COURSE_ID"));
                    officeHours.setOwnerId(andrew);
                    return new ScannedQRCode(officeHours);
            }
        }
        catch (IOException | ParseException e) {
            e.printStackTrace();
            Log.e("ScannedCodeFactory", "Unable to process QR Code because data is not in the right format.");
        }
        return null;
    }
}
