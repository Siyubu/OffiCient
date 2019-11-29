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

public class Course implements Scannable {
    private int id;
    private String title, code;
    private Term term;

    public Course(int id) {
        this.id = id;
    }

    public Course (int courseId, String courseTitle, String courseCode, Term courseTerm) {
        id = courseId;
        title = courseTitle;
        code = courseCode;
        term = courseTerm;
    }

    public Course (String courseTitle, String courseCode, Term courseTerm) { // Used to create a new instance to be added to the server
        id = -1;
        title = courseTitle;
        code = courseCode;
        term = courseTerm;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    @NonNull
    @Override
    public String toString() {
        return title + " - " + term;
    }
}
