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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import edu.cmu.officient.api.qrcode.ScannedQRCode;
import edu.cmu.officient.model.*;

public abstract class OfficientStorage{
    public static final int STATE_STARTED = 1, STATE_STOPPED = -1, STATE_EXPIRED = 2;
    private static List<ScannedQRCode> scannedQRCodes = new ArrayList<>();


    static SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd/MM/yy HH:mm:ss", Locale.getDefault());
    public Assignment getAssignment(int id) { // Builds an object using the ID
        return new Assignment();
    }

    public Course getCourse(int id) {
        return new Course();
    }

    public OfficeHours getOfficeHours(int id) {
        return new OfficeHours();
    }

    public User getUser(int id) {
        return new Student(1, "siyubu", "Solange Iyubu", "", "");
    }

    public User getCurrentUser(){
        return new Student(1, "siyubu", "Solange Iyubu", "", "");
    }

    public static List<ScannedQRCode> getScannedQRCodes() {
        return scannedQRCodes;
    }

    public static void setScannedQRCodes(List<ScannedQRCode> scannedQRCodes) {
        OfficientStorage.scannedQRCodes = scannedQRCodes;
    }

    public int processScannedCode(ScannedQRCode code){
        // First check if it is inside the List
        for (ScannedQRCode scannedCode : scannedQRCodes) {
            if (scannedCode.equals(code)) {
                // Check the state and do what is required
                if (scannedCode.getState() == scannedCode.TIMER_STARTED) {
                    scannedCode.setState(scannedCode.TIMER_STOPPING);
                    scannedCode.run(); // Execute the action in Stopping to stop it
                    return STATE_STOPPED;
                }
                // Here we had the data but it has already been stopped
            }
        }
        // Not found
        scannedQRCodes.add(code); // Code state should be starting, so now we run the action
        if (code.getState() == code.TIMER_STARTING) {
            code.run(); // Should be in STARTED state now
            return STATE_STARTED;
        }
        else
            return STATE_EXPIRED;
    }
}
