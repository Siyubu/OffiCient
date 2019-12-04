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

import edu.cmu.officient.logic.ApplicationManager;

public class TimerStarting extends TimerState {

    public TimerStarting(ScannedQRCode code) {
        super(code);
    }

    @Override
    public void execute(Context context) {
        // Call the method to store the record to the database
        long id = ApplicationManager.getInstance().storeTask(context, getScannedCode().getData());
        getScannedCode().setState(getScannedCode().TIMER_STARTED);
        getScannedCode().setRecordId(id);
    }
}
