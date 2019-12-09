/*
 *
 *  * @author Segla Boladji Vinny Trinite Adjibi
 *  * AndrewID : vadjibi
 *  * Program : MSIT
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

package edu.cmu.officient.api.qrcode;

import android.content.Context;

import org.json.JSONException;

import edu.cmu.officient.DBCommunication.RequestData;
import edu.cmu.officient.logic.ApplicationManager;

public class TimerStopping extends TimerState {

    public TimerStopping(ScannedQRCode code) {
        super(code);
    }

    @Override
    public void execute(Context context) {
        // Every DB actions should be done here
        ApplicationManager.getInstance(context).endTask(context, getScannedCode().getData(), getScannedCode().getRecordId());
        getScannedCode().setState(getScannedCode().TIMER_STOPPED);
    }

}
