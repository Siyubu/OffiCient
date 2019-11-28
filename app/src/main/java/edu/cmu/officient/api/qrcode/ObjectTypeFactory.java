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
import edu.cmu.officient.model.OfficeHours;
import edu.cmu.officient.model.Scannable;

public class ObjectTypeFactory {
    public static ObjectType getObjectType(Scannable scannable) {
        if (scannable instanceof Assignment)
            return ObjectType.ASSIGNMENT;
        else if (scannable instanceof OfficeHours)
            return ObjectType.OFFICE_HOURS;
        else
            return ObjectType.UNDEFINED;
    }
}
