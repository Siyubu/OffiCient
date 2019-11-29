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
import edu.cmu.officient.wservices.*;

public class ScannedCodeFactory {
    public static ScannedQRCode getCodeObject(ObjectType type, int id, int userId) {
        OfficientStorage storage = new PermanentStorage();
        User user = storage.getUser(userId);
        Scannable data = null;
        switch (type) {
            case ASSIGNMENT:
                data = storage.getAssignment(id);
                break;
            case OFFICE_HOURS:
                data = storage.getOfficeHours(id);
        }
        return new ScannedQRCode(data, user);
    }
}
