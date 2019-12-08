/*
 *
 *  * @author Segla Boladji Vinny Trinite Adjibi
 *  * AndrewID : vadjibi
 *  * Program : MSIT
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

package edu.cmu.officient.networktasks;

import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.lang.ref.WeakReference;

import edu.cmu.officient.DBCommunication.RequestData;
import edu.cmu.officient.R;

public abstract class StandardRequestTask extends AsyncTask<String, String, String> {
    WeakReference<AppCompatActivity> activityReference;
    private View progress, root;
    private JSONObject data;

    public StandardRequestTask(AppCompatActivity activity, View root, View progressMarker) {
        activityReference = new WeakReference<>(activity);
        this.progress = progressMarker;
        this.root = root;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Hide the view
        if (progress != null)
            progress.setVisibility(View.VISIBLE);
    }

    @Override
    protected String doInBackground(String... args) {
        AppCompatActivity activity = activityReference.get();
        String[] attributes = getRequestParameters();
        RequestData requestData = new RequestData( activity,"http://gamfruits.com/officient_api/functions.php", attributes, args);
        data = requestData.getResponse();
        if( data != null){
            return getOutput();
        }
        else {
            return  "error";
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        AppCompatActivity activity = activityReference.get();
        if(s.equalsIgnoreCase("error")){
            Toast.makeText(activity, R.string.network_unavailable, Toast.LENGTH_SHORT).show();
        }
        else if (s.equalsIgnoreCase("no_data")) {
            Toast.makeText(activity, R.string.data_empty, Toast.LENGTH_SHORT).show();
        }
        else { // This is not a common case
            processData(s);
        }
        if (progress != null)
            progress.setVisibility(View.GONE);
    }

    abstract protected String getOutput();
    abstract protected void processData(String s);
    abstract protected String[] getRequestParameters();

    public AppCompatActivity getBaseActivity() {
        return activityReference.get();
    }


    public View getProgress() {
        return progress;
    }

    public void setProgress(View progress) {
        this.progress = progress;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    public View getRoot() {
        return root;
    }

}
