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
import edu.cmu.officient.model.User;

public class ScannedQRCode {
    private TimerState state;
    private User owner; // Represents the owner of the QR Code (Generally an instructor)
    private Scannable data;

    public ScannedQRCode(Scannable object){
        data = object;
        state = new TimerPaused(this);
    }

    public ScannedQRCode(Scannable object, User user) {
        this(object);
        owner = user;
        // When here, we need to check in which state we should put the object
    }

    public ScannedQRCode(Scannable object, User user, TimerState state) {
        data = object;
        owner = user;
        state = state;
    }

    public void run(){
        state.execute();
    }
}
