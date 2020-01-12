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

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import edu.cmu.officient.logic.ApplicationManager;
import edu.cmu.officient.storage.OfficientLocalDbContract.*;
import edu.cmu.officient.util.Time;

public class OfficeHours implements Scannable, Serializable {
    private int day; // Day is a 0 based value of the day in the week
    private String venue, description, id, ownerId, courseId;
    private Time startAt, endAt;

    public OfficeHours() {

    }

    public OfficeHours(String id, int day, String venue, String description,  String ownerId, String courseId, Time startAt, Time endAt) {
        this.day = day;
        this.venue = venue;
        this.description = description;
        this.id = id;
        this.ownerId = ownerId;
        this.courseId = courseId;
        this.startAt = startAt;
        this.endAt = endAt;
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
    public String getCollectionName() {
        return ScannedOfficeHours.TABLE_NAME;
    }

    @Override
    public ContentValues getStorableData() {
        ContentValues values = new ContentValues();
        values.put(ScannedOfficeHours.COL_OH_ID, id);
        values.put(ScannedOfficeHours.COL_INSTRUCTOR, ownerId);
        values.put(ScannedOfficeHours.COL_COURSE_ID, courseId);
        return values;
    }

    @Override
    public Map<String, Object> getStorableDataMap() {
        Map<String, Object> data = new HashMap<>();
        data.put("user", ApplicationManager.getInstance(null).getLoggedInUser().getAndrewId());
        data.put("office_hour_id", "" + id);
        data.put("held_by", ownerId);
        data.put("course_id", courseId);
        return data;
    }

    public String getType(){
        return "Office Hours";
    }

    public String getId() {
        return id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public void setId(String id) {
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

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof OfficeHours) {
            OfficeHours o = (OfficeHours) obj;
            /*private int day; // Day is a 0 based value of the day in the week
            private String venue, description, id, ownerId, courseId;
            private Time startAt, endAt;*//*
            boolean ans = id.equals(o.id) && expectedTime==o.expectedTime && publishedOn.equals(o.publishedOn) &&
                    deadline.equals(o.deadline) && availableTill.equals(o.availableTill) && title.equals(o.title);
            return ans;*/
            return id.equals(o.id);
        }
        return false;
    }


    @Override
    public String toString() {
        return "Office Hours - " + ownerId;
    }
}
