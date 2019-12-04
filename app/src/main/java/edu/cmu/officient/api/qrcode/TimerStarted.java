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

public class TimerStarted extends TimerState {

    public TimerStarted(ScannedQRCode code) {
        super(code);
    }

    @Override
    public void execute(Context context) {

    }
}
