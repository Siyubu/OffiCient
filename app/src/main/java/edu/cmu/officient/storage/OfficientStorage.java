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

import java.text.SimpleDateFormat;
import java.util.Locale;

import edu.cmu.officient.model.*;

public abstract class OfficientStorage{
    static SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd/MM/yy HH:mm:ss", Locale.getDefault());
    public Assignment getAssignment(int id) { // Builds an object using the ID
        return new Assignment();
    }

    public Course getCourse(int id) {
        return new Course();
    }

    public OfficeHours getOfficeHours(int id) {
        return new OfficeHours();
    }

    public User getUser(int id) {
        return new Student(1, "siyubu", "Solange Iyubu", "", "");
    }

    public User getCurrentUser(){
        return new Student(1, "siyubu", "Solange Iyubu", "", "");
    }
}
