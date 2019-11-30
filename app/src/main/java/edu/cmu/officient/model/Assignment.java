/*
 *
 *  * @author Segla Boladji Vinny Trinite Adjibi, Wuyeh Jobe
 *  * AndrewID : vadjibi, jwuyeh
 *  * Program : MSIT
 *  *
 *  * On our honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

package edu.cmu.officient.model;

import androidx.annotation.Nullable;

import java.util.Date;

public class Assignment implements Scannable {
    private int id, expectedTime; // Expected time in hours
    Date publishedOn, deadline, availableTill;
    private String title;
    private Course course;

    public Assignment() { // Constructs an empty object - Just for testing now

    }

    public Assignment(int id, int expectedTime, Date publishedOn, Date deadline, Date availableTill,
                      String title, Course course) {
        this.id = id;
        this.expectedTime = expectedTime;
        this.publishedOn = publishedOn;
        this.deadline = deadline;
        this.availableTill = availableTill;
        this.title = title;
        this.course = course;
    }

    @Override
    public boolean isInRange() {
        Date now = new Date();
        return now.after(publishedOn) && now.before(availableTill);
    }

    @Override
    public boolean userCanAccess(int id) {
        // Based on if the student is in the class, returns true
        return true;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Assignment) {

        }
        return super.equals(obj);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExpectedTime() {
        return expectedTime;
    }

    public void setExpectedTime(int expectedTime) {
        this.expectedTime = expectedTime;
    }

    public Date getPublishedOn() {
        return publishedOn;
    }

    public void setPublishedOn(Date publishedOn) {
        this.publishedOn = publishedOn;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getAvailableTill() {
        return availableTill;
    }

    public void setAvailableTill(Date availableTill) {
        this.availableTill = availableTill;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return title;
    }
}
