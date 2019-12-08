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

import android.util.Log;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.transform.Templates;

import edu.cmu.officient.util.DateConversion;

public class Course /*implements Scannable*/ {
    private int id;
    private String title, code;
    private Term term;
    private List<Instructor> instructors = new ArrayList<>();
    private List<Student> students = new ArrayList<>();
    private List<OfficeHours> officeHours = new ArrayList<>();
    private List<Assignment> assignments = new ArrayList<>();

    public Course(){

    }

    public Course(int id, String title, String code, Term term, List<Instructor> instructors, List<Student> students, List<OfficeHours> oh)
    {
        this(id, title, code, term);
        this.instructors = instructors;
        this.students = students;
        officeHours = oh;
    }

    public Course(int id, String title, String code, Term term, List<Assignment> assignments, List<OfficeHours> oh)
    {
        this(id, title, code, term);
        this.assignments = assignments;
        officeHours = oh;
    }


    public Course (int id, String title, String code, Term term) {
        this(id, title, code);
        this.term = term;
    }

    public Course(int id, String title, String code)
    {
        this.id = id;
        this.title = title;
        this.code = code;
    }

    /*@Override
    public boolean isInRange() { // Get the list of all office hours and check whether it's okay
        // Should check if there is a valid office hours being held at the moment
        return true;
    }

    @Override
    public boolean userCanAccess(int id) {
        // Returns true if the user can access a certain office hour i.e there are registered in the class
        return isAStudentOfCourse(id);
    }*/

    public boolean isAStudentOfCourse(int id) {
        for (Student student : students) {
            if (student.getId() == id)
                return true;
        }
        return false;
    }

    public boolean isAStudentOfCourse(Student student) {
        for (Student _student : students) {
            if (student.equals(_student))
                return true;
        }
        return false;
    }

    /*public OfficeHours getAppropriateOfficeHours(int instructorId) {
        for (OfficeHours oh : officeHours) {
            if (oh.isInRange() && oh.getHolder().getId() == instructorId)
                return oh;
        }
        return null;
    }*/

    public boolean equals(Object obj) {
        if (obj instanceof Course) {
            Course course = (Course) obj;
            return id==course.id && title.equals(course.title) && code.equals(course.code) && term.equals(course.term);
        }
        return false;
    }

    /*@Override
    public String getType() {
        return "Assignment";
    }*/

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

    public List<Instructor> getInstructors() {
        return instructors;
    }

    public void setInstructors(List<Instructor> instructors) {
        this.instructors = instructors;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<OfficeHours> getOfficeHours() {
        return officeHours;
    }

    public void setOfficeHours(List<OfficeHours> officeHours) {
        this.officeHours = officeHours;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    @NonNull
    @Override
    public String toString() {
        return title + " - " + term;
    }
}
