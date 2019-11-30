/*
 *
 *  * @author Segla Boladji Vinny Trinite Adjibi
 *  * AndrewID : vadjibi
 *  * Program : MSIT
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

package edu.cmu.officient.model;

import androidx.annotation.NonNull;

import java.util.List;

public class Course implements Scannable {
    private int id;
    private String title, code;
    private Term term;
    private List<Instructor> instructors;

    public Course(){

    }

    public Course(int id, String title, String code, Term term, List<Instructor> instructors) {
        this.id = id;
        this.title = title;
        this.code = code;
        this.term = term;
        this.instructors = instructors;
    }

    @Override
    public boolean isInRange() { // Get the list of all office hours and check whether it's okay
        // Should check if there is a valid office hours being held at the moment
        return true;
    }

    @Override
    public boolean userCanAccess(int id) {
        // Returns true if the user can access a certain office hour i.e there are registered in the class
        return true;
    }

    @NonNull
    @Override
    public String toString() {
        return title + " - " + term;
    }
}
