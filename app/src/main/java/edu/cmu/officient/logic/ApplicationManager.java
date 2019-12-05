/*
 *
 *  * @author Segla Boladji Vinny Trinite Adjibi
 *  * AndrewID : vadjibi
 *  * Program : MSIT
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

package edu.cmu.officient.logic;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.cmu.officient.R;
import edu.cmu.officient.api.qrcode.ScannedQRCode;
import edu.cmu.officient.model.Scannable;
import edu.cmu.officient.model.User;
import edu.cmu.officient.storage.OfficientLocalDbHelper;
import edu.cmu.officient.storage.SQLiteStorage;

public class ApplicationManager {
    private static final ApplicationManager APPLICATION_MANAGER = new ApplicationManager();
    private List<ScannedQRCode> scannedQRCodes = new ArrayList<>();


    public static ApplicationManager getInstance() {
        return APPLICATION_MANAGER;
    }

    private ApplicationManager() {
    }

    public ScannedCodeStatus processScannedCode(Context context, ScannedQRCode code){
        // First check if it is inside the List
        for (ScannedQRCode scannedCode : scannedQRCodes) {
            if (scannedCode.equals(code)) {
                // Check the state and do what is required
                if (scannedCode.getState() == scannedCode.TIMER_STARTED) {
                    scannedCode.setState(scannedCode.TIMER_STOPPING);
                    scannedCode.run(context); // Execute the action in Stopping to stop it
                    return ScannedCodeStatus.STOPPED;
                }
                // Here we had the data but it has already been stopped
            }
        }
        // Not found
        scannedQRCodes.add(code); // Code state should be starting, so now we run the action
        if (code.getState() == code.TIMER_STARTING) {
            code.run(context); // Should be in STARTED state now
            return ScannedCodeStatus.RUNNING;
        }
        else
            return ScannedCodeStatus.EXPIRED;
    }

    public ScannedCodeStatus processScannedCode(Context context, int position) {
        return processScannedCode(context, scannedQRCodes.get(position));
    }

    public List<ScannedQRCode> getOngoingActivities(){
        ArrayList<ScannedQRCode> activities = new ArrayList<>();
        for (ScannedQRCode code : scannedQRCodes) {
            if (code.getState() == code.TIMER_STARTED)
                activities.add(code);
        }
        return activities;
    }

    public void logUserIn(JSONObject response, Context context){
        SharedPreferences preferences = context.getSharedPreferences(context.getString(R.string.prefs_file_name), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        // Start storing all the data
        try {
            editor.putInt("user.id", Integer.parseInt(response.getString("id")))
                    .putString("user.andrew_id", response.getString("andrewId"))
                    .putString("user.name", response.getString("name"))
                    .putString("user.alt_email", response.getString("alternative_email"))
                    .putString("user.phone_number", response.getString("phoneNumber"));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        editor.apply();
    }

    public User getLoggedInUser(Context context){
        SharedPreferences preferences = context.getSharedPreferences(context.getString(R.string.prefs_file_name), Context.MODE_PRIVATE);
        String andrewId = preferences.getString("user.andrew_id", null);
        if (andrewId != null) {
            // We have the user data so we can now build their object
            int userId = preferences.getInt("user.id", -1);
            String name = preferences.getString("user.name", null);
            String altEmail = preferences.getString("user.alt_email", null);
            String phoneNumber = preferences.getString("user.phone_number", null);
            return new User(userId, andrewId, name, altEmail, phoneNumber);
        }
        return null;
    }

    public long storeTask(Context context, Scannable scannable) {
        Date now = new Date();
        // For the static storage only
        SQLiteDatabase database = new OfficientLocalDbHelper(context).getWritableDatabase();
        SQLiteStorage storage = new SQLiteStorage(database);
        return storage.addTaskRecord(scannable, now);
    }

    public void endTask(Context context, Scannable scannable, long id) {
        Date now = new Date();
        SQLiteDatabase database = new OfficientLocalDbHelper(context).getWritableDatabase();
        SQLiteStorage storage = new SQLiteStorage(database);
        storage.updateTaskRecord(id, now, scannable);
    }
}
