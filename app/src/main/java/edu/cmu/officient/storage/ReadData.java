/*
 *
 *  * @author Segla Boladji Vinny Trinite Adjibi
 *  * AndrewID : vadjibi
 *  * Program : MSIT
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

package edu.cmu.officient.storage;

import edu.cmu.officient.model.*;

public interface ReadData {
    Assignment getAssignment(int id);
    OfficeHours getOfficeHours(int id);
    Course getCourse(int id);
    User getUser(int id);
    User getCurrentUser();
}
