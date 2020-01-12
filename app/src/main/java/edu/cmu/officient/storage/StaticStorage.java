/*
 *
 *  * @author Segla Boladji Vinny Trinite Adjibi
 *  * AndrewID : vadjibi
 *  * Program : MSIT
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

package edu.cmu.officient.storage;


import android.util.Log;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.cmu.officient.api.qrcode.ScannedQRCode;
import edu.cmu.officient.model.*;

public class StaticStorage extends OfficientStorage implements ReadData, UpdateData, DeleteData, AddData{
    public Assignment getAssignment(int id) { // Builds an object using the ID
        try {
            return new Assignment(id, 3, DATE_FORMATTER.parse("08/11/2019 10:00:00"), DATE_FORMATTER.parse("01/12/2019 11:59:00"), DATE_FORMATTER.parse("08/12/2019 13:00:00"), "Location Aware", getCourse(1));
        }
        catch (ParseException e) {
            Log.e("STATIC_GENERATOR", e.toString());
        }
        return null;
    }

    public Course getCourse(int id) {
        Term term;
        try {
            term = new Term("Fall 2019", DATE_FORMATTER.parse("08/01/2019 10:00:00"), DATE_FORMATTER.parse("08/09/2019 11:59:00"));
        }
        catch (ParseException e) {
            term = new Term();
        }
        List<Instructor> instructors = new ArrayList<>();
        return new Course(1, "Design Patterns for SD", "18785", term, instructors, new ArrayList<Student>(), new ArrayList<OfficeHours>());
    }

    @Override
    public void addTaskRecord(ScannedQRCode code, Date date) {

    }

    @Override
    public void updateTaskRecord(String id, Date endDate, Scannable scannable) {

    }


}
