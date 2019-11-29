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

    @NonNull
    @Override
    public String toString() {
        return title + " - " + term;
    }
}
