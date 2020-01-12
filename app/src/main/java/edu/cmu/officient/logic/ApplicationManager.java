/*
 *
 *  * @author Segla Boladji Vinny Trinite Adjibi
 *  * AndrewID : vadjibi
 *  * Program : MSIT
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

/*
 *
 *  * @author Wuyeh Jobe
 *  * AndrewID : jwuyeh
 *  * Program : MSIT
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

package edu.cmu.officient.logic;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.cmu.officient.R;
import edu.cmu.officient.api.qrcode.ScannedQRCode;
import edu.cmu.officient.model.Scannable;
import edu.cmu.officient.model.User;
import edu.cmu.officient.storage.FirestoreStorage;

public class ApplicationManager {
    private static ApplicationManager APPLICATION_MANAGER;
    private static List<ScannedQRCode> scannedQRCodes = new ArrayList<>();
    private Context context;
    private User LOGGED_IN_USER = null;

    public static ApplicationManager getInstance(Context context) {
        if (APPLICATION_MANAGER == null) {
            APPLICATION_MANAGER = new ApplicationManager(context);
        }
        return APPLICATION_MANAGER;
    }

    private ApplicationManager(Context context) {
        this.context = context;
    }

    public ScannedCodeStatus processScannedCode(ScannedQRCode code){
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

    public ScannedCodeStatus processScannedCode(int position) {
        return processScannedCode(scannedQRCodes.get(position));
    }

    public List<ScannedQRCode> getOngoingActivities(){
        ArrayList<ScannedQRCode> activities = new ArrayList<>();
        for (ScannedQRCode code : scannedQRCodes) {
            if (code.getState() == code.TIMER_STARTED)
                activities.add(code);
        }
        return activities;
    }

    public void logUserIn(JSONObject response, boolean isFaculty){
        SharedPreferences preferences = context.getSharedPreferences(context.getString(R.string.prefs_file_name), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        // Start storing all the data
        try {
            editor.putInt("user.id", Integer.parseInt(response.getString("id")))
                    .putString("user.andrew_id", response.getString("andrewId"))
                    .putString("user.name", response.getString("name"))
                    .putString("user.alt_email", response.getString("alternative_email"))
                    .putString("user.phone_number", response.getString("phoneNumber"))
                    .putBoolean("user.is_faculty", isFaculty);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        editor.apply();
    }

    public void logUserIn(String name, String alt_email, String phoneNumber){
        SharedPreferences preferences = context.getSharedPreferences(context.getString(R.string.prefs_file_name), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        // Start storing all the data
        editor.putString("user.name", name)
                .putString("user.alt_email", alt_email)
                .putString("user.phone_number", phoneNumber);
        editor.apply();
    }

    public void logUserIn(User user) {
        SharedPreferences preferences = context.getSharedPreferences(context.getString(R.string.prefs_file_name), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        // Start storing all the data
        editor.putInt("user.id", user.getId())
                .putString("user.andrew_id", user.getAndrewId())
                .putString("user.name", user.getFullname())
                .putString("user.alt_email", user.getAlternativeEmail())
                .putString("user.phone_number", user.getPhoneNumber())
                .putBoolean("user.is_faculty", user.isFaculty());
        editor.apply();
    }

    public boolean logUserOut(){
        SharedPreferences preferences = context.getSharedPreferences(context.getString(R.string.prefs_file_name), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("user.name");
        editor.remove("user.id");
        editor.remove("user.andrew_id");
        editor.remove("user.is_faculty");
        editor.remove("user.alt_email");
        editor.remove("user.phone_number");
        editor.apply();

        FirebaseAuth.getInstance().signOut();
        return true;
    }

    public User getLoggedInUser(){
        if (LOGGED_IN_USER == null) {
            SharedPreferences preferences = context.getSharedPreferences(context.getString(R.string.prefs_file_name), Context.MODE_PRIVATE);
            String andrewId = preferences.getString("user.andrew_id", null);
            if (andrewId != null) {
                // We have the user data so we can now build their object
                int userId = preferences.getInt("user.id", -1);
                String name = preferences.getString("user.name", null);
                String altEmail = preferences.getString("user.alt_email", null);
                String phoneNumber = preferences.getString("user.phone_number", null);
                boolean isFaculty = preferences.getBoolean("user.is_faculty", false);
                return new User(userId, andrewId, name, altEmail, phoneNumber, isFaculty);
            }
        }
        return LOGGED_IN_USER;
    }

    public void storeTask(ScannedQRCode code) {
        Date now = new Date();
        Scannable scannable = code.getData();
        // For the static storage only
        /*SQLiteDatabase database = new OfficientLocalDbHelper(context).getWritableDatabase();
        SQLiteStorage storage = new SQLiteStorage(database);
        return storage.addTaskRecord(code, now);*/
        new FirestoreStorage().addTaskRecord(code, now);
    }

    public void endTask(Scannable scannable, String id) {
        Date now = new Date();
        /*SQLiteDatabase database = new OfficientLocalDbHelper(context).getWritableDatabase();
        SQLiteStorage storage = new SQLiteStorage(database);
        storage.updateTaskRecord(id, now, scannable);*/
        new FirestoreStorage().updateTaskRecord(id, now, scannable);
    }
}
