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
 *  * @author Segla Boladji Vinny Trinite Adjibi
 *  * AndrewID : vadjibi
 *  * Program : MSIT
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

package edu.cmu.officient.misc;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class DataPersistenceReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Here we shall retrieve all the data and store then in the remote server
        if (intent.getAction() != null && intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            // Here we collect all the data from the database to be stored on the server
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            PendingIntent alarmIntent;
        }
    }
}
