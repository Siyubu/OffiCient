/*
 *
 *  * @author Segla Boladji Vinny Trinite Adjibi
 *  * AndrewID : vadjibi
 *  * Program : MSIT
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

package edu.cmu.officient.wservices;

import edu.cmu.officient.model.Assignment;
import edu.cmu.officient.model.OfficeHours;

public class PermanentStorage implements DataStorage {
    public Assignment getAssignment(int id) { // Builds an object using the ID

        return new Assignment();
    }

    public OfficeHours getOfficeHours(int id) {
        return new OfficeHours();
    }
}
