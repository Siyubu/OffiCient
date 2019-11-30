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

public final class TimerStateFactory {
    public static TimerState getState(ScannedQRCode code) {
        User user = code.getOwner();
        Scannable data = code.getData();
        if (data.isInRange()) {
            if (data.userCanAccess(user.getId())) {
                return new TimerStopped(code);
            }
            return new TimerInvalid(code, "You cannot access this object. Permission denied.");
        }
        return new TimerInvalid(code, "The data you're trying to access is no longer valid.");
    }
}
