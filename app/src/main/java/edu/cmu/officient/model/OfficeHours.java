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

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class OfficeHours implements Scannable {
    private int id, day; // Day is a 0 based value of the day in the week
    private String venue, description;
    private Date startAt, endAt;
    private Course course;
    private Instructor holder; // Instructor holding the office hours

    public OfficeHours() {

    }

    public OfficeHours(int id, int day, String venue, String description,  Date startAt, Date endAt,
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
            // Now we check the time
            Date now = new Date();
            if (startAt.before(now) && endAt.after(now) ) { // We are still in the range
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean userCanAccess(int id) {
        // Check if it is a student in the class
        return course.isAStudentOfCourse(id);
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

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
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
