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
import edu.cmu.officient.model.User;

public interface ReadData {
    Assignment getAssignment(int id);
    OfficeHours getOfficeHours(int id);
    User getUser(int id);
}
