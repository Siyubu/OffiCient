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

import edu.cmu.officient.api.qrcode.ScannedQRCode;
import edu.cmu.officient.model.*;
import edu.cmu.officient.storage.OfficientLocalDbContract.*;

public class SQLiteStorage extends OfficientStorage implements AddData, ReadData, UpdateData, DeleteData {
    private SQLiteDatabase database;

    public SQLiteStorage(SQLiteDatabase database) {
        this.database = database;
    }

    public void addTaskRecord(ScannedQRCode code, Date date){
        // Create the query and store the data
        Scannable scannable = code.getData();
        ContentValues values = scannable.getStorableData();

        // Actual start date
        values.put(ScannedAssignment.COL_STARTED_AT, date.toString());
        try {
            database.insert(scannable.getCollectionName(), null, values);
        }
        catch (SQLException e) {
            Log.e(getClass().getSimpleName(), e.getMessage(), e);
        }
    }

    public void updateTaskRecord(String id, Date endDate, Scannable scannable) {
        ContentValues values = new ContentValues();
        values.put(ScannedAssignment.COL_LEFT_AT, endDate.toString());
        try {
            database.update(scannable.getCollectionName(), values, ScannedAssignment._ID + " = " + id, null);
        }
        catch (SQLException e) {
            Log.e(getClass().getSimpleName(), e.getMessage(), e);
        }
    }
}
