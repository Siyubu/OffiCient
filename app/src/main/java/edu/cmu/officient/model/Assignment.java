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

import java.time.LocalDateTime;

public class Assignment implements Scannable {
    private int id, expectedTime; // Expected time in hours
    LocalDateTime publishedOn, deadline, availableTill;
    private String title;
    private Course course;

    public Assignment() { // Constructs an empty object - Just for testing now

    }

    public Assignment(int id, int expectedTime, LocalDateTime publishedOn, LocalDateTime deadline, LocalDateTime availableTill,
                      String title, Course course) {
        this.id = id;
        this.expectedTime = expectedTime;
        this.publishedOn = publishedOn;
        this.deadline = deadline;
        this.availableTill = availableTill;
        this.title = title;
        this.course = course;
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

    public LocalDateTime getPublishedOn() {
        return publishedOn;
    }

    public void setPublishedOn(LocalDateTime publishedOn) {
        this.publishedOn = publishedOn;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public LocalDateTime getAvailableTill() {
        return availableTill;
    }

    public void setAvailableTill(LocalDateTime availableTill) {
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
