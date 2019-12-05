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

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import edu.cmu.officient.storage.OfficientLocalDbContract.*;

public class OfficientLocalDbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_FILE_NAME = "officient.db";

    private final String[] CREATE_QUERIES = {
            "CREATE TABLE " + HistoryEntry.TABLE_NAME + " (" + HistoryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + HistoryEntry.COL_PARAMETER + " TEXT NOT NULL, " +
                    HistoryEntry.COL_RETRIEVED_AT + " DATETIME NOT NULL, " + HistoryEntry.COL_VALUE + " INTEGER NOT NULL" + " )",

            "CREATE TABLE " + ScannedAssignment.TABLE_NAME + " (" + ScannedAssignment._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ScannedAssignment.COL_ASSIGNMENT_TITLE + " TEXT NOT NULL, " +
                    ScannedAssignment.COL_ASSIGNMENT_ID + " INTEGER NOT NULL, " + ScannedAssignment.COL_COURSE_ID + " INTEGER NOT NULL, " + ScannedAssignment.COL_COURSE_TITLE + " TEXT NOT NULL, " +
                    ScannedAssignment.COL_DEADLINE + " DATETIME NOT NULL, " + ScannedAssignment.COL_PUBLICATION_DATE + " DATETIME NOT NULL, " + ScannedAssignment.COL_LATE_SUBMISSION_DATE + " DATETIME, " +
                    ScannedAssignment.COL_STARTED_AT + " DATETIME NOT NULL, " + ScannedAssignment.COL_LEFT_AT + " DATETIME" + " )",

            "CREATE TABLE " + ScannedOfficeHours.TABLE_NAME + " ( " + ScannedOfficeHours._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ScannedOfficeHours.COL_OH_ID + " INTEGER NOT NULL, " +
                    ScannedOfficeHours.COL_INSTRUCTOR_ID + " INTEGER NOT NULL, " + ScannedOfficeHours.COL_INSTRUCTOR + " TEXT NOT NULL, " + ScannedOfficeHours.COL_COURSE_ID + " INTEGER NOT NULL, " +
                    ScannedOfficeHours.COL_COURSE_TITLE + " TEXT NOT NULL, " + ScannedOfficeHours.COL_STARTED_AT + " DATETIME NOT NULL, " + ScannedOfficeHours.COL_LEFT_AT + " DATETIME" + ")"

    };

    private final String[] DELETE_QUERIES = {
            "DELETE TABLE IF EXISTS " + HistoryEntry.TABLE_NAME,
            "DELETE TABLE IF EXISTS " + ScannedAssignment.TABLE_NAME,
            "DELETE TABLE IF EXISTS " + ScannedOfficeHours.TABLE_NAME
    };

    public OfficientLocalDbHelper(Context context) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase database) {
        for (String query : CREATE_QUERIES) {
            try {
                database.execSQL(query);
            }
            catch (SQLException e) {
                Log.e(getClass().getSimpleName(), e.getMessage(), e);
            }
        }
    }

    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        for (String query : DELETE_QUERIES) {
            try {
                database.execSQL(query);
            }
            catch (SQLException e) {
                Log.e(getClass().getSimpleName(), e.getMessage(), e);
            }
        }
        onCreate(database);
    }
}
