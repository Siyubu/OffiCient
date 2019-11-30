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

public class TimerStopping extends TimerState {

    public TimerStopping(ScannedQRCode code) {
        super(code);
    }

    @Override
    public void execute() {
        // Every DB actions should be done here
        getScannedCode().setState(getScannedCode().TIMER_STOPPED);
    }
}
