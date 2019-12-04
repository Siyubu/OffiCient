/*
 *
 *  * @author Segla Boladji Vinny Trinite Adjibi
 *  * AndrewID : vadjibi
 *  * Program : MSIT
 *  *
 *  * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

package edu.cmu.officient.exception;

import android.util.Log;

public abstract class BuilderException extends OfficientException {
    public void fix(){
        Log.e(getClass().getSimpleName(), getMessage(), this);
    }
}
