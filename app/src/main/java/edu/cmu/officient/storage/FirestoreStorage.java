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
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.Map;

import edu.cmu.officient.api.qrcode.ScannedQRCode;
import edu.cmu.officient.model.Scannable;
import edu.cmu.officient.storage.OfficientLocalDbContract.ScannedAssignment;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class FirestoreStorage extends OfficientStorage implements AddData, ReadData, UpdateData, DeleteData {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

     synchronized public void addTaskRecord(final ScannedQRCode code, Date date){
        // Create the query and store the data
        Scannable scannable = code.getData();
        Map<String, Object> data = scannable.getStorableDataMap();
        data.put("started_at", date);
        db.collection(scannable.getCollectionName())
                .add(data)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        code.setRecordId(documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    public void updateTaskRecord(String id, Date endDate, Scannable scannable) {
        db.collection(scannable.getCollectionName())
                .document(id)
                .update("ended_at", endDate)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void vdd) {
                        Log.d(TAG, "Updated successfully");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }
}
