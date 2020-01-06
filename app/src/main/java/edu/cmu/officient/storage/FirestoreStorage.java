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
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.Map;

import edu.cmu.officient.model.Scannable;
import edu.cmu.officient.storage.OfficientLocalDbContract.ScannedAssignment;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class FirestoreStorage extends OfficientStorage implements AddData, ReadData, UpdateData, DeleteData {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public long addTaskRecord(Scannable scannable, Date date){
        // Create the query and store the data

        Map<String, Object> data = scannable.getStorableDataMap();

        db.collection(scannable.getLocalDatabaseName())
                .add(data)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });

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
            //database.update(scannable.getLocalDatabaseName(), values, ScannedAssignment._ID + " = " + id, null);
        }
        catch (SQLException e) {
            Log.e(getClass().getSimpleName(), e.getMessage(), e);
        }
    }
}
