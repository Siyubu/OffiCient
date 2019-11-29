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

public class OfficeHours implements Scannable {
    private int id;
    private String venue, description;
    private LocalDateTime startAt, endAt;
    private Course course;
    private Instructor holder; // Instructor holding the office hours

    public OfficeHours() {

    }

    public OfficeHours(int id, String venue, String description, LocalDateTime startAt, LocalDateTime endAt,
                       Course course, Instructor holder) {
        this.id = id;
        this.venue = venue;
        this.description = description;
        this.startAt = startAt;
        this.endAt = endAt;
        this.course = course;
        this.holder = holder;
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

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public void setStartAt(LocalDateTime startAt) {
        this.startAt = startAt;
    }

    public LocalDateTime getEndAt() {
        return endAt;
    }

    public void setEndAt(LocalDateTime endAt) {
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        return "OfficeHours{}";
    }
}
