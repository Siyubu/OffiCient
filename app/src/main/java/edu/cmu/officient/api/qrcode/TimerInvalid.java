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

public class TimerInvalid extends TimerState {
    private String errorMessage;

    public TimerInvalid(ScannedQRCode code, String message) {
        super(code);
        errorMessage = message;
    }


    @Override
    public void execute() {

    }
}
