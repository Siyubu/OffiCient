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

import android.app.Service;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

public class DataPersistenceService extends Service {
    public DataPersistenceService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        if (intent.getAction().equals("SYNC_DATA")) {
            // Let's get the data here and schedule our worker
            WorkManager workManager = WorkManager.getInstance(this);
            OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(DataPersistenceWorker.class)
                    .setInputData(getDataToSave()).build();
            workManager.enqueue(request)
            ;
        }
        return null;
    }

    private Data getDataToSave(){
        Data.Builder builder = new Data.Builder();
        builder.putStringArray("OFFICE_HOURS", new String[] {"1, 2, 3", "5, 6, 4"});
        return builder.build();
    }

    /*@Override
    public boolean onStartJob(JobParameters jobParameters) {
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }*/
}
