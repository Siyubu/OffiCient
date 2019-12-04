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

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.Date;

import edu.cmu.officient.model.Assignment;
import edu.cmu.officient.model.Scannable;
import edu.cmu.officient.storage.OfficientLocalDbContract.*;

public class SQLiteStorage extends OfficientStorage implements AddData, ReadData, UpdateData, DeleteData {
    private SQLiteDatabase database;

    public SQLiteStorage(SQLiteDatabase database) {
        this.database = database;
    }

    public long addTaskRecord(Scannable scannable, Date date){
        // Create the query and store the data
        ContentValues values = scannable.getStorableData();

        // Actual start date
        values.put(ScannedAssignment.COL_STARTED_AT, date.toString());
        try {
            return database.insert(scannable.getLocalDatabaseName(), null, values);
        }
        catch (SQLException e) {
            Log.e(getClass().getSimpleName(), e.getMessage(), e);
        }
        return -1;
    }

    public void updateTaskRecord(long id, Date endDate, Scannable scannable) {
        ContentValues values = new ContentValues();
        values.put(ScannedAssignment.COL_LEFT_AT, endDate.toString());
        try {
            database.update(scannable.getLocalDatabaseName(), values, ScannedAssignment._ID + " = " + id, null);
        }
        catch (SQLException e) {
            Log.e(getClass().getSimpleName(), e.getMessage(), e);
        }
    }
}
