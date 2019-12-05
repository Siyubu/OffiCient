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


import java.util.Date;

import edu.cmu.officient.model.Scannable;

public class RemoteStorage extends OfficientStorage implements ReadData, UpdateData, DeleteData, AddData{
    @Override
    public void updateTaskRecord(long id, Date endDate, Scannable scannable) {

    }

    @Override
    public long addTaskRecord(Scannable scannable, Date date) {
        return 0;
    }
}
