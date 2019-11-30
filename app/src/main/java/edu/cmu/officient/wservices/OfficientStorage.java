/*
 *
 *  * @author Segla Boladji Vinny Trinite Adjibi
 *  * AndrewID : vadjibi
 *  * Program : MSIT
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

package edu.cmu.officient.wservices;

import edu.cmu.officient.model.*;

public abstract class OfficientStorage{
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
        return new Student();
    }

    public User getCurrentUser(){
        return new Student();
    }
}
