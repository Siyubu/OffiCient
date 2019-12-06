/*
 *
 *  * @author Wuyeh Jobe
 *  * AndrewID : jwuyeh
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

package edu.cmu.officient.DBCommunication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import edu.cmu.officient.model.Course;

public class CheckInternetConnection {

    private Context context;
    private final static CheckInternetConnection INSTANCE = new CheckInternetConnection();

    private CheckInternetConnection(){

    }
    private CheckInternetConnection(Context context){
        this.context = context;
    }

    public boolean isAvailable() {
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    public static CheckInternetConnection getInstance(){
        return INSTANCE;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Context getContext(){
        return context;
    }
}
