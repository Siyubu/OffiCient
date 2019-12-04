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
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import edu.cmu.officient.model.*;
import edu.cmu.officient.storage.*;

public class ScannedCodeFactory {
    public static ScannedQRCode loadCode(String output) {
        Properties properties = new Properties();
        OfficientStorage storage = new StaticStorage();
        try {
            properties.load(new StringReader(output));
            // Collect the type and process it
            ObjectType type = ObjectType.fromString(properties.getProperty("TYPE"));
            // Get the common data as well
            int id = Integer.parseInt(properties.getProperty("ID"));
            String name = properties.getProperty("NAME");

            SimpleDateFormat formatter = new SimpleDateFormat();
            switch (type){
                case ASSIGNMENT:
                    // Get additional data like Course name
                    Date deadline = formatter.parse(properties.getProperty("DEADLINE")), availableTill = formatter.parse(properties.getProperty("AVAILABLE_TILL")),
                            publishedOn = formatter.parse(properties.getProperty("PUBLISHED_DATE"));

                    Assignment assignment = new Assignment();
                    assignment.setId(id);
                    assignment.setTitle(name);
                    assignment.setAvailableTill(availableTill);
                    assignment.setDeadline(deadline);
                    assignment.setPublishedOn(publishedOn);
                    return new ScannedQRCode(assignment);
                case OFFICE_HOURS:
                    LocalTime startAt = LocalTime.parse(properties.getProperty("START_AT")), endAt = LocalTime.parse(properties.getProperty("END_AT"));
                    int day = Integer.parseInt(properties.getProperty("DAY")), instructorId = Integer.parseInt(properties.getProperty("OWNER_ID"));
                    User user = storage.getUser(instructorId);

                    OfficeHours officeHours = new OfficeHours();
                    officeHours.setId(id);
                    officeHours.setStartAt(startAt);
                    officeHours.setEndAt(endAt);
                    officeHours.setDay(day);
                    officeHours.setHolder((Instructor) user);
                    break;
            }
        }
        catch (IOException | ParseException e) {
            Log.e("ScannedCodeFactory", "Unable to process QR Code because data is not in the right format.");
        }
        return null;
    }
}
