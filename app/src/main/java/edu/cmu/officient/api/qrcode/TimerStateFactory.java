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

import edu.cmu.officient.model.*;

public final class TimerStateFactory { // Should be a polymorphic behavior
    public static TimerState getState(ScannedQRCode code) {
        User user = code.getOwner();
        Scannable data = code.getData();
        if (data.isInRange()) {
            int userId = -1;
            if (user != null)
                userId = user.getId();
            if (data.userCanAccess(userId)) {
                return code.TIMER_STARTING;
            }
        }
        return code.TIMER_INVALID;
    }
}
