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

import android.content.ContentValues;

import java.util.Calendar;

import edu.cmu.officient.storage.OfficientLocalDbContract.*;
import edu.cmu.officient.util.Time;

public class OfficeHours implements Scannable {
    private int id, day; // Day is a 0 based value of the day in the week
    private String venue, description;
    private Time startAt, endAt;
    private Course course;
    private Instructor holder; // Instructor holding the office hours

    public OfficeHours() {

    }

    public OfficeHours(int id, int day, String venue, String description,  Time startAt, Time endAt,
                       Course course, Instructor holder) {
        this.id = id;
        this.venue = venue;
        this.description = description;
        this.day = day;
        this.startAt = startAt;
        this.endAt = endAt;
        this.course = course;
        this.holder = holder;
    }

    @Override
    public boolean isInRange() {
        Calendar calendar = Calendar.getInstance();
        if (calendar.get(Calendar.DAY_OF_WEEK) == day ) { // We are the right day when the office hours is held
            Time now = Time.now();
            return now.compareTo(startAt) >=0 && now.compareTo(endAt) <=0;
        }
        return false;
    }

    @Override
    public boolean userCanAccess(int id) {
        // Check if it is a student in the class
        /*return course.isAStudentOfCourse(id);*/
        return true;
    }

    @Override
    public String getLocalDatabaseName() {
        return ScannedOfficeHours.TABLE_NAME;
    }

    @Override
    public ContentValues getStorableData() {
        ContentValues values = new ContentValues();
        values.put(ScannedOfficeHours.COL_OH_ID, id);
        values.put(ScannedOfficeHours.COL_INSTRUCTOR, holder.getFullname());
        values.put(ScannedOfficeHours.COL_INSTRUCTOR_ID, holder.getId());
        if (course != null) {
            values.put(ScannedOfficeHours.COL_COURSE_ID, course.getId());
            values.put(ScannedOfficeHours.COL_COURSE_TITLE, course.getTitle());
        }
        return values;
    }

    public String getType(){
        return "Office Hours";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Time getStartAt() {
        return startAt;
    }

    public void setStartAt(Time startAt) {
        this.startAt = startAt;
    }

    public Time getEndAt() {
        return endAt;
    }

    public void setEndAt(Time endAt) {
        this.endAt = endAt;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Instructor getHolder() {
        return holder;
    }

    public void setHolder(Instructor holder) {
        this.holder = holder;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }



    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        return "OfficeHours{}";
    }
}
