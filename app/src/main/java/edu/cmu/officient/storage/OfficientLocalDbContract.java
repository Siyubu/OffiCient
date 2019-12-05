/*
 *
 *  * @author Segla Boladji Vinny Trinite Adjibi
 *  * AndrewID : vadjibi
 *  * Program : MSIT
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

package edu.cmu.officient.storage;

import android.provider.BaseColumns;

public class OfficientLocalDbContract {
    private OfficientLocalDbContract() {

    }

    /*
        Table used to store the data that have been last retrieved from the remote database.
     */
    public static class HistoryEntry implements BaseColumns {
        public final static String TABLE_NAME = "values_retrieved";
        public final static String COL_PARAMETER = "parameter";
        public final static String COL_VALUE = "current_value";
        public final static String COL_RETRIEVED_AT = "last_retrieved_at";
    }

    /*
        Table used to store all the data related to the assignments QR Codes scanned by the user.
     */
    public static class ScannedAssignment implements BaseColumns {
        public final static String TABLE_NAME = "assignment_records";
        public final static String COL_ASSIGNMENT_ID = "assignment_id";
        public final static String COL_ASSIGNMENT_TITLE = "assignment_title";
        public final static String COL_COURSE_ID = "course_id";
        public final static String COL_COURSE_TITLE = "course_title";
        public final static String COL_DEADLINE = "deadline";
        public final static String COL_PUBLICATION_DATE = "published_at";
        public final static String COL_LATE_SUBMISSION_DATE = "available_till";
        public final static String COL_STARTED_AT = "started_at";
        public final static String COL_LEFT_AT = "end_at";
    }

    /*
        Table used to store all the data related to course/instructor sessions scanned by students (i.e Office Hours)
     */
    public static class ScannedOfficeHours implements BaseColumns {
        public final static String TABLE_NAME = "office_hours_records";
        public final static String COL_OH_ID = "office_hours_id";
        public final static String COL_INSTRUCTOR = "instructor_name";
        public final static String COL_INSTRUCTOR_ID = "instructor_id";
        public final static String COL_COURSE_TITLE = "course_title";
        public final static String COL_COURSE_ID = "course_id";
        public final static String COL_STARTED_AT = "started_at";
        public final static String COL_LEFT_AT = "end_at";
    }
}
