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

import edu.cmu.officient.model.Assignment;
import edu.cmu.officient.model.Course;
import edu.cmu.officient.model.Scannable;

public final class TimerStateFactory {
    public static TimerState getState(ScannedQRCode code) {
        Scannable data = code.getData();
        if (data instanceof Assignment) {
            // First we check the published date and maximum submission time

        }
        else if (data instanceof Course) {

        }
        return new TimerInvalid(code, "Class not supported for now");
    }
}
