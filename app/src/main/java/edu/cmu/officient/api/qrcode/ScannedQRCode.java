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

import edu.cmu.officient.model.Scannable;

public class ScannedQRCode {
    private TimerState state;
    private Scannable data;

    public ScannedQRCode(Scannable object){
        data = object;
        state = new TimerPaused(this);
    }
}
